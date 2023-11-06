package com.logicalj.bms.showtimeservice.config;

import com.logicalj.bms.showtimeservice.model.TheaterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="theaters-service")
public interface TheaterServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/theaters-service/api/v1/theaters/{theaterId}")
    ResponseEntity<TheaterDto> getTheaterDetails(@PathVariable Integer theaterId);

    @RequestMapping(method = RequestMethod.GET, value = "/theaters-service/api/v1/theaters/query")
    ResponseEntity<PagedModel<TheaterDto>> queryTheaters(@RequestParam(defaultValue = "1", required = false) Integer city,
                                                         @RequestParam(defaultValue = "0", required = false) int page,
                                                         @RequestParam(defaultValue = "2000", required = false) int size,
                                                         @RequestParam(defaultValue = "", required = false) List<String> sortList,
                                                         @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sortOrder);
}