package com.logicalj.bms.showtimeservice.model;

import com.logicalj.bms.showtimeservice.entity.SeatTypeEnum;
import com.logicalj.bms.showtimeservice.entity.Show;
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
