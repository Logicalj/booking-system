package com.logicalj.bms.bookingservice.model;

import com.logicalj.bms.bookingservice.entity.Booking;
import com.logicalj.bms.bookingservice.entity.BookingStatusEnum;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class BookingDto {

    private int noOfSeats;
    private Date startDate;
    private Date endDate;
    private Date bookingDateTime;
    private BookingStatusEnum bookingStatus;
    private Integer showId;
    private Integer userId;

    public Booking toBooking() {
        return Booking.builder()
                .startDate(this.startDate)
                .endDate(this.endDate)
                .showId(this.showId)
                .bookingStatus(BookingStatusEnum.LOCKED)
                .bookingDateTime(this.bookingDateTime)
                .noOfSeats(this.noOfSeats)
                .userId(this.userId)
                .versionId(1)
                .build();
    }
}