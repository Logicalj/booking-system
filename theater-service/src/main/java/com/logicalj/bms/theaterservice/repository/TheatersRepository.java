package com.logicalj.bms.theaterservice.repository;

import com.logicalj.bms.theaterservice.entity.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatersRepository extends JpaRepository<Theater, Integer> {

    Optional<Theater> findByNameAndCityId(String name, Integer cityId);

    Page<Theater> findByCityId(Integer city, Pageable pageable);

}
