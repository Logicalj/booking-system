package com.logicalj.bms.theaterservice.model;


import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Set;

@Data
public class TheaterDto extends RepresentationModel<TheaterDto> {

    private Integer theaterId;
    private String name;
    private Integer cityId;
    private List<CinemaHallDto> cinemaHalls;


}
