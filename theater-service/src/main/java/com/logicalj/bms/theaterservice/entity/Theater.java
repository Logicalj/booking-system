package com.logicalj.bms.theaterservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "theater", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Cinemahall> cinemahalls = new ArrayList<>();
    private Integer cityId;

    public void addCinemaHalls(Cinemahall hall){
        cinemahalls.add(hall);
        hall.setTheater(this);
    }

    public void removeCinemaHalls(Cinemahall hall){
        cinemahalls.remove(hall);
        hall.setTheater(this);
    }

}
