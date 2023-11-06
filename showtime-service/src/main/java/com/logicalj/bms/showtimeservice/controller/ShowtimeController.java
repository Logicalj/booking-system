package com.logicalj.bms.showtimeservice.controller;

import com.logicalj.bms.showtimeservice.entity.Show;
import com.logicalj.bms.showtimeservice.exception.NoDataFoundException;
import com.logicalj.bms.showtimeservice.model.ResponseDto;
import com.logicalj.bms.showtimeservice.model.ShowDto;
import com.logicalj.bms.showtimeservice.model.UpdateShowTicketsDto;
import com.logicalj.bms.showtimeservice.service.ShowtimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/showtimes")
public class ShowtimeController extends BaseController {

    @Autowired
    ShowtimeService showtimeService;

    @Autowired
    ShowModelAssembler showModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Show> pagedResourcesAssembler;

    private final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping("/addShow")
    public ResponseEntity<ResponseDto> addShow(@RequestBody ShowDto showDto) throws NoDataFoundException {
        Show s = showtimeService.addShow(showDto);
        return ResponseEntity.ok(new ResponseDto(true, "Show Added Successfully, with Show Id:" + s.getShowId()));
    }

    @PutMapping("/updateAvailableTickets")
    public ResponseEntity<ResponseDto> updateAvailableTickets(@RequestBody UpdateShowTicketsDto updateShowTicketsDto) {
        showtimeService.updateAvailableTickets(updateShowTicketsDto);
        return ResponseEntity.ok(new ResponseDto(true, "Successfully Updated Available Tickets"));
    }

    @GetMapping("/query")
    public PagedModel<ShowDto> fetchTheaterRunningShowInTown(
            @RequestParam(value = "city", defaultValue = "1", required = false) Integer city,
            @RequestParam(value = "movieId", defaultValue = "1", required = false) Integer movieId,
            @RequestParam(value="showTime", defaultValue = "2023-11-04", required = false) String expectedShowTimeString,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "30", required = false) int size,
            @RequestParam(defaultValue = "", required = false) List<String> sortList,
            @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sortOrder) throws ParseException {
        log.info("GET theater invoked for City: {}, MovieId:{}, BookingDate:{}, Page: {}, Size: {}, SortList: {}, SortDirection: {}",city, movieId, expectedShowTimeString, page, size, sortList, sortOrder);
        Date expectedShowTime = validateRequestParamsAndTransform(expectedShowTimeString);
        Page<Show> showDetails = showtimeService.fetchShowsWithFilters(city, movieId, expectedShowTime, page, size, sortList, sortOrder.toString());
        return pagedResourcesAssembler.toModel(showDetails, showModelAssembler);
    }

    private Date validateRequestParamsAndTransform(String expectedShowTimeString) throws ParseException {
        return DF.parse(expectedShowTimeString);
    }


}
