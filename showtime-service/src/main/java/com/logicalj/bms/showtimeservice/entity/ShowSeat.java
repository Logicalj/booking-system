package com.logicalj.bms.showtimeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;
    private SeatTypeEnum seatType;
    private Integer price;
    private Integer cinemaSeatId;
    @Column(nullable = false)
    private Integer showId;
    private Integer bookingId;


}
