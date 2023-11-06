package com.logicalj.bms.showtimeservice.service;

import com.logicalj.bms.showtimeservice.config.TheaterServiceClient;
import com.logicalj.bms.showtimeservice.entity.Show;
import com.logicalj.bms.showtimeservice.exception.NoDataFoundException;
import com.logicalj.bms.showtimeservice.model.CinemaHallDto;
import com.logicalj.bms.showtimeservice.model.ShowDto;
import com.logicalj.bms.showtimeservice.model.TheaterDto;
import com.logicalj.bms.showtimeservice.model.UpdateShowTicketsDto;
import com.logicalj.bms.showtimeservice.repository.ShowtimeRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private TheaterServiceClient theaterServiceProxy;

    @Autowired
    private final CircuitBreakerFactory circuitBreakerFactory;

    public Show addShow(ShowDto showDto) throws NoDataFoundException {
        Show show = showDto.toShow();
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");

        circuitBreaker.run(() -> {
            ResponseEntity<TheaterDto> theaterDtoResponseEntity = theaterServiceProxy.getTheaterDetails(showDto.getTheaterId());
            if (theaterDtoResponseEntity.getStatusCode().is2xxSuccessful()) {
                TheaterDto theater = theaterDtoResponseEntity.getBody();

                Optional<CinemaHallDto> cinemaHall = theater.getCinemaHalls()
                        .stream()
                        .filter(e -> e.getCinemahallId().equals(showDto.getCinemaHallId()))
                        .findFirst();

                show.setAvailableTickets(cinemaHall.get().getTotalSeats());
            }
            return null;
        }, throwable -> {
            if(throwable instanceof FeignException && ((FeignException) throwable).status() == 400){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unavailable Tickets");
            }
            throw new RuntimeException(throwable);
        });

        return showtimeRepository.save(show);
    }


    public Page<Show> fetchShowsWithFilters(Integer city, Integer movieId, Date bookingDate, int page, int size, List<String> sortList, String sortOrder) {
        Collection<TheaterDto> listOfTheaters = null;
        int pageNo = 0;
        Optional<PagedModel<TheaterDto>> theaterDtoOptional = recursiveScrollThroughAllPages(city, pageNo);
        if(theaterDtoOptional.isPresent()) {
            listOfTheaters = new ArrayList<>();
            listOfTheaters.addAll(theaterDtoOptional.get().getContent());
            while (theaterDtoOptional.get().getNextLink().isPresent()) {
                pageNo++;
                Optional<PagedModel<TheaterDto>> dto = recursiveScrollThroughAllPages(city, pageNo);
                if (dto.isPresent()) {
                    listOfTheaters.addAll(dto.get().getContent());
                }
            }
        }
        List<Integer> theaterIds = listOfTheaters.stream().map(e -> e.getTheaterId()).collect(Collectors.toList());
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return showtimeRepository.findAllByMovieIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndTheaterIdIn(movieId, bookingDate, bookingDate, theaterIds, pageable);
    }

    public void updateAvailableTickets(UpdateShowTicketsDto updateShowTicketsDto) {
        try {
            Show showtime = showtimeRepository.findById(updateShowTicketsDto.getId()).get();
            if (showtime.getAvailableTickets() + updateShowTicketsDto.getDifference() < 0)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Args");
            showtime.setAvailableTickets(showtime.getAvailableTickets() + updateShowTicketsDto.getDifference());
            showtimeRepository.save(showtime);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    private Optional<PagedModel<TheaterDto>> recursiveScrollThroughAllPages(Integer city, Integer pageNumber){
        ResponseEntity<PagedModel<TheaterDto>> theaterDtoResponseEntity = theaterServiceProxy.queryTheaters(city, pageNumber, 2000, Collections.emptyList(), Sort.Direction.DESC);
        if (theaterDtoResponseEntity.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(theaterDtoResponseEntity.getBody());
        }
        return Optional.empty();
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
