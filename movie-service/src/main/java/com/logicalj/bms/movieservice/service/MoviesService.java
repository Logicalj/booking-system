package com.logicalj.bms.movieservice.service;

import com.logicalj.bms.movieservice.entity.GenreEnum;
import com.logicalj.bms.movieservice.entity.LanguageEnum;
import com.logicalj.bms.movieservice.entity.Movie;
import com.logicalj.bms.movieservice.model.MovieDto;
import com.logicalj.bms.movieservice.repository.MoviesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    public Movie saveMovie(MovieDto movieDto) {
        return moviesRepository.save(getEntity(movieDto));
    }

    private Movie getEntity(MovieDto movieDto){
        Movie m = new Movie();
        BeanUtils.copyProperties(movieDto, m);

        m.setGenre(GenreEnum.valueOf(movieDto.getGenre()));
        m.setLanguage(LanguageEnum.valueOf(movieDto.getLanguage()));

        return m;
    }

}
