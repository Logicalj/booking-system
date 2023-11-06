package com.logicalj.bms.theaterservice.controller.query;

import com.logicalj.bms.theaterservice.controller.BaseController;
import com.logicalj.bms.theaterservice.entity.Theater;
import com.logicalj.bms.theaterservice.exception.NoDataFoundException;
import com.logicalj.bms.theaterservice.model.TheaterDto;
import com.logicalj.bms.theaterservice.service.TheatersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/theaters")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "false")
public class TheaterQueryController extends BaseController {

    @Autowired
    TheatersService theatersService;

    @Autowired
    private TheaterModelAssembler theaterModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Theater> pagedResourcesAssembler;

    @GetMapping("/{id}")
    public ResponseEntity<TheaterDto> getTheaterById(@PathVariable Integer id) throws NoDataFoundException {
        log.info("GET theater invoked for Id: {}", id);
        Theater t = theatersService.getEntityById(id);
        return ResponseEntity.ok(theaterModelAssembler.toModel(t));
    }


    @GetMapping("/query")
    public PagedModel<TheaterDto> fetchTheatersWithPagination(
            @RequestParam(defaultValue = "1", required = false) Integer city,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "30", required = false) int size,
            @RequestParam(defaultValue = "", required = false) List<String> sortList,
            @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sortOrder)  {
        log.info("GET theater invoked for City: {}, Page: {}, Size: {}, SortList: {}, SortDirection: {}",city, page, size, sortList, sortOrder);
        Page<Theater> theaterPage = theatersService.fetchTheatersWithFilters(city, page, size, sortList, sortOrder.toString());
        return pagedResourcesAssembler.toModel(theaterPage, theaterModelAssembler);
    }

}