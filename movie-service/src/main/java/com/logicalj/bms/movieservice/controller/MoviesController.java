package com.logicalj.bms.movieservice.controller;

import com.logicalj.bms.movieservice.entity.Movie;
import com.logicalj.bms.movieservice.model.MovieDto;
import com.logicalj.bms.movieservice.service.MoviesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/movies")
public class MoviesController extends BaseController{

    @Autowired
    MoviesService moviesService;

    @PostMapping("/")
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movie){
        log.info("POST movie invoked with title: {}", movie.getTitle());
        Movie m = new Movie();
        m = moviesService.saveMovie(movie);
        BeanUtils.copyProperties(m, movie);
        return ResponseEntity.ok(movie);
    }




}
