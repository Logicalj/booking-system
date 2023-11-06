package com.logicalj.bms.theaterservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "cinemahall")
public class Cinemahall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cinemahallId;
    @Column(unique = true, nullable = false)
    private String name;
    private Integer totalSeats;
    @OneToMany(mappedBy = "cinemahall", cascade = {CascadeType.ALL})
    private List<CinemaSeat> cinemaSeats = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    Theater theater;

    public void addCinemaSeat(CinemaSeat cinemaSeat){
        cinemaSeats.add(cinemaSeat);
        cinemaSeat.setCinemahall(this);
    }

    public void removeCinemaSeat(CinemaSeat cinemaSeat){
        cinemaSeats.remove(cinemaSeat);
        cinemaSeat.setCinemahall(this);
    }
}
