package com.logicalj.bms.showtimeservice.repository;

import com.logicalj.bms.showtimeservice.entity.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShowtimeRepository extends JpaRepository<Show, Integer> {

    Page<Show> findAllByMovieIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndTheaterIdIn(Integer movieId, Date bookingDate1, Date bookingDate2, List<Integer> theaterIds, Pageable pageable);

}
