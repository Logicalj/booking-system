package com.logicalj.bms.theaterservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logicalj.bms.theaterservice.entity.CinemaSeat;
import com.logicalj.bms.theaterservice.entity.SeatTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Set;

@Data
public class CinemaSeatDto {

    private Integer cinemaSeatId;
    private String seatNo;
    private SeatTypeEnum seatType;
    @JsonIgnore
    private Integer cinemaHallId;

}
