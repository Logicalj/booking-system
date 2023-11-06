package com.logicalj.bms.theaterservice.service;

import com.logicalj.bms.theaterservice.entity.CinemaSeat;
import com.logicalj.bms.theaterservice.entity.Cinemahall;
import com.logicalj.bms.theaterservice.entity.Theater;
import com.logicalj.bms.theaterservice.exception.NoDataFoundException;
import com.logicalj.bms.theaterservice.model.CinemaHallDto;
import com.logicalj.bms.theaterservice.model.CinemaSeatDto;
import com.logicalj.bms.theaterservice.model.TheaterDto;
import com.logicalj.bms.theaterservice.repository.CinemaHallRepository;
import com.logicalj.bms.theaterservice.repository.CinemaSeatRepository;
import com.logicalj.bms.theaterservice.repository.TheatersRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class TheatersService {

    @Autowired
    private TheatersRepository theatersRepository;

    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @Autowired
    private CinemaSeatRepository cinemaSeatRepository;

    public Theater saveEntity(TheaterDto theaterDto) {
        log.info("Save entity invoked for theaters");

        Theater theater = new Theater();
        BeanUtils.copyProperties(theaterDto, theater);
        for (CinemaHallDto cinemaHallDto : theaterDto.getCinemaHalls()) {
            Cinemahall cinemaHall = new Cinemahall();
            BeanUtils.copyProperties(cinemaHallDto, cinemaHall);

            for (CinemaSeatDto cinemaSeatDto : cinemaHallDto.getCinemaSeats()) {
                CinemaSeat seat = new CinemaSeat();
                BeanUtils.copyProperties(cinemaSeatDto, seat);
                cinemaHall.addCinemaSeat(seat);
            }
            theater.addCinemaHalls(cinemaHall);
        }

        return theatersRepository.save(theater);
    }

    public Theater processEntity(TheaterDto theaterDto) {
        Optional<Theater> result = theatersRepository.findByNameAndCityId(theaterDto.getName(), theaterDto.getCityId());

        if (result.isPresent()) {
            log.info("Going to perform update");
            Theater theaterEntity = result.get();
            theaterEntity.setCinemahalls(new ArrayList<>());
            log.info("Removed existing");
            theatersRepository.save(theaterEntity);
            theaterEntity.setTheaterId(theaterEntity.getTheaterId());
        }
        return saveEntity(theaterDto);
    }

    public Theater getEntityById(Integer id) throws NoDataFoundException {
        Optional<Theater> theater = theatersRepository.findById(id);
        if (theater.isEmpty()) {
            throw new NoDataFoundException("No data found for provided id: " + id);
        }
        return theater.get();
    }

    public Page<Theater> fetchTheatersWithFilters(Integer city, int page, int size, List<String> sortList, String sortOrder) {
        // create Pageable object using the page, size and sort details
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        // fetch the page object by additionally passing pageable with the filters
        return theatersRepository.findByCityId(city, pageable);
    }

    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }

}
