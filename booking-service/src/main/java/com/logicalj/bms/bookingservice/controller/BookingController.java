package com.logicalj.bms.bookingservice.controller;

import com.logicalj.bms.bookingservice.model.BookingDto;
import com.logicalj.bms.bookingservice.model.ResponseDto;
import com.logicalj.bms.bookingservice.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/booking")
public class BookingController extends BaseController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/addReservation")
    public ResponseEntity<ResponseDto> addReservation(@RequestBody BookingDto bookingDto) {
        bookingService.addReservation(bookingDto);
        return ResponseEntity.ok(new ResponseDto(true, "Reservation Added Successfully"));
    }
}
