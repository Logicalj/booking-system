package com.logicalj.bms.theaterservice.controller.command;

import com.logicalj.bms.theaterservice.controller.BaseController;
import com.logicalj.bms.theaterservice.entity.Theater;
import com.logicalj.bms.theaterservice.model.TheaterDto;
import com.logicalj.bms.theaterservice.service.TheatersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/theaters")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "true")
public class TheaterCommandController extends BaseController {

    @Autowired
    TheatersService theatersService;

    @PostMapping("/")
    public ResponseEntity<Integer> saveMovie(@RequestBody TheaterDto theaterDto){
        log.info("POST theater invoked with Name: {}", theaterDto.getName());
        Theater t = theatersService.processEntity(theaterDto);
        return ResponseEntity.ok(t.getTheaterId());
    }

}
