package com.logicalj.bms.movieservice.repository;

import com.logicalj.bms.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movie, Integer> {

}
