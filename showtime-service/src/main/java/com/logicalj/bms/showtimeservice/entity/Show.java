package com.logicalj.bms.showtimeservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;

import java.util.Date;

@Data
@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;
    private Date dateTime;
    private Date startTime;
    private Date endTime;
    @Column(nullable = false)
    private Integer movieId;
    @Column(nullable = false)
    private Integer theaterId;
    @Column(nullable = false)
    private Integer cinemaHallId;
    @Column(nullable = false)
    private Integer availableTickets;
    @Column(nullable = false)
    private Integer price;


}
