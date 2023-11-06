package com.logicalj.bms.showtimeservice.model;

import com.logicalj.bms.showtimeservice.entity.Show;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
public class ShowDto extends RepresentationModel<ShowDto> {

    private Integer showId;
    private Date dateTime;
    private Date startTime;
    private Date endTime;
    private Integer movieId;
    private Integer theaterId;
    private Integer cinemaHallId;
    private Integer availableTickets;
    private Integer price;

    public Show toShow() {
        Show show = new Show();
        BeanUtils.copyProperties(this, show);
        return show;
    }

}