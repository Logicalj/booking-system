package com.logicalj.bms.theaterservice.controller.query;

import com.logicalj.bms.theaterservice.entity.CinemaSeat;
import com.logicalj.bms.theaterservice.entity.Cinemahall;
import com.logicalj.bms.theaterservice.entity.Theater;
import com.logicalj.bms.theaterservice.model.CinemaHallDto;
import com.logicalj.bms.theaterservice.model.CinemaSeatDto;
import com.logicalj.bms.theaterservice.model.TheaterDto;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class extends RepresentationModelAssemblerSupport which is required for Pagination.
 * It converts the Customer Entity to the Customer Model and has the code for it
 */
@Component
public class TheaterModelAssembler extends RepresentationModelAssemblerSupport<Theater, TheaterDto> {
    public TheaterModelAssembler() {
        super(TheaterQueryController.class, TheaterDto.class);
    }

    @Override
    public TheaterDto toModel(Theater entity) {
        TheaterDto model = new TheaterDto();
        BeanUtils.copyProperties(entity, model);

        List<CinemaHallDto> hallList = entity.getCinemahalls()
                .stream()
                .map(this::entityToDtoCinemahall)
                .collect(Collectors.toList());

        model.setCinemaHalls(hallList);
        return model;
    }

    private CinemaHallDto entityToDtoCinemahall(Cinemahall cinemahall) {
        CinemaHallDto hallDto = new CinemaHallDto();
        BeanUtils.copyProperties(cinemahall, hallDto);

        List<CinemaSeatDto> seatList = cinemahall.getCinemaSeats()
                .stream()
                .map(this:: entityToDtoCinemaSeat)
                .collect(Collectors.toList());

        hallDto.setCinemaSeats(seatList);
        return hallDto;
    }

    private CinemaSeatDto entityToDtoCinemaSeat(CinemaSeat cinemaSeat) {
        CinemaSeatDto seatDto = new CinemaSeatDto();
        BeanUtils.copyProperties(cinemaSeat, seatDto);
        return seatDto;
    }
}
