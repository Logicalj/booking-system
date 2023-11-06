package com.logicalj.bms.theaterservice.model;


import lombok.Data;

import java.util.List;

@Data
public class CinemaHallDto {

    private Integer cinemahallId;
    private String name;
    private Integer totalSeats;
    private List<CinemaSeatDto> cinemaSeats;


}
