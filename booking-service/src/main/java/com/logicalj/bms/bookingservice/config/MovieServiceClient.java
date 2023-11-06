package com.logicalj.bms.bookingservice.config;

import com.logicalj.bms.bookingservice.model.TheaterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="movies-service")
public interface MovieServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/movies-service/api/v1/theaters/{theaterId}")
    ResponseEntity<TheaterDto> getTheaterDetails(@PathVariable Integer theaterId);
}