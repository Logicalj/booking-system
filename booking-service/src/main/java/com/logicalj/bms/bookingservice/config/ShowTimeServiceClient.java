package com.logicalj.bms.bookingservice.config;

import com.logicalj.bms.bookingservice.model.TheaterDto;
import com.logicalj.bms.bookingservice.model.UpdateShowTicketsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="showtime-service")
public interface ShowTimeServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/showtime-service/api/v1/showtimes")
    ResponseEntity<TheaterDto> getTheaterDetails(@PathVariable Integer theaterId);

    @RequestMapping(method = RequestMethod.PUT, value="/showtime-service/api/v1/showtimes/updateAvailableTickets")
    void updateAvailableTickets(@RequestBody UpdateShowTicketsDto updateShowTicketsDto);

}