package com.logicalj.bms.bookingservice.model;

import lombok.Data;


@Data
public class ShowtimeDto {
    public class ShowSeat {

        private Integer showSeatId;
        private SeatTypeEnum seatType;
        private Integer price;
        private Integer cinemaSeatId;
        private Integer showId;
        private Integer bookingId;
    }


}
