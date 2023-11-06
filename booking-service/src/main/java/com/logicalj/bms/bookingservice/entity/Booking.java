package com.logicalj.bms.bookingservice.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    @Column(nullable = false)
    private Integer noOfSeats;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    @Column(nullable = false)
    private Date bookingDateTime;
    @Enumerated(EnumType.STRING)
    private BookingStatusEnum bookingStatus;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer showId;
    private Integer versionId;
    private Integer price;

}
