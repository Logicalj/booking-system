package com.logicalj.bms.bookingservice.service;

import com.logicalj.bms.bookingservice.config.ShowTimeServiceClient;
import com.logicalj.bms.bookingservice.entity.Booking;
import com.logicalj.bms.bookingservice.model.*;
import com.logicalj.bms.bookingservice.repository.BookingRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowTimeServiceClient showTimeServiceClient;

    @Autowired
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    StreamBridge bridge;

    public void addReservation(BookingDto bookingDto) {
        try {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");

            circuitBreaker.run(() -> {
                showTimeServiceClient.updateAvailableTickets(new UpdateShowTicketsDto(bookingDto.getShowId(), -bookingDto.getNoOfSeats()));
                return null;
            }, throwable -> {
                if (throwable instanceof FeignException && ((FeignException) throwable).status() == 400)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unavailable Tickets");
                throw new RuntimeException(throwable);
            });
            Booking b = bookingRepository.save(bookingDto.toBooking());
            bridge.send("producer-out-0", b.toString());
        } catch (ResponseStatusException e) {
            log.error("Exception caught with reason: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Exception caught with reason: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }

}
