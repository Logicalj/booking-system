package com.logicalj.bms.bookingservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CinemaSeatDto {

    private Integer cinemaSeatId;
    private String seatNo;
    private SeatTypeEnum seatType;
    @JsonIgnore
    private Integer cinemaHallId;

}
