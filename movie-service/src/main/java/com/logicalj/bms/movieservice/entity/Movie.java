package com.logicalj.bms.movieservice.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue
    private Integer movieId;
    @Column(nullable = false, unique = true)
    private String title;
    private String description;
    private LocalTime duration;
    @Enumerated(EnumType.STRING)
    private LanguageEnum language;
    private Date releaseDate;
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

}
