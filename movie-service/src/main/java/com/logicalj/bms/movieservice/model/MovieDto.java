package com.logicalj.bms.movieservice.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class MovieDto {

    private Integer movieId;
    private String title;
    private String description;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime duration;
    private String language;
    private Date releaseDate;
    private String genre;


}
