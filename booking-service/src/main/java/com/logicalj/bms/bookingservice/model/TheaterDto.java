package com.logicalj.bms.bookingservice.model;


import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class TheaterDto extends RepresentationModel<TheaterDto> {

    private Integer theaterId;
    private String name;
    private Integer cityId;
    private List<CinemaHallDto> cinemaHalls;


}
