package com.logicalj.bms.showtimeservice.model;


import lombok.Data;

import java.util.List;

@Data
public class CinemaHallDto {

    private Integer cinemahallId;
    private String name;
    private Integer totalSeats;
    private List<CinemaSeatDto> cinemaSeats;

}
