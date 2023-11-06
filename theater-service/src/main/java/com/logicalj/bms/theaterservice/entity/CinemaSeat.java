package com.logicalj.bms.theaterservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name="cinemaSeat")
public class CinemaSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cinemaSeatId;
    private String seatNo;
    private SeatTypeEnum seatType;
    @ManyToOne
    @JoinColumn(name="cinemahall_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    Cinemahall cinemahall;
}
