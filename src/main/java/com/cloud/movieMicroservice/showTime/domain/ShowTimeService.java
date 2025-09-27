package com.cloud.movieMicroservice.showTime.domain;

import com.cloud.movieMicroservice.movie.domain.Movie;
import com.cloud.movieMicroservice.movie.infrastructure.MovieRepository;
import com.cloud.movieMicroservice.showTime.infrastructure.ShowTimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimeService {

    private final ShowTimeRepository showTimeRepository;
    private final MovieRepository movieRepository;

    public ShowTimeService(ShowTimeRepository showTimeRepository, MovieRepository movieRepository) {
        this.showTimeRepository = showTimeRepository;
        this.movieRepository = movieRepository;
    }

    public List<ShowTime> getShowTimesByMovie(Long movieId) {
        return showTimeRepository.findByMovieId(movieId);
    }


    public ShowTime createShowTime(Long movieId, ShowTime showTime) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        showTime.setMovie(movie);
        if (movie.getPremiere()) {
            showTime.setPrice(25.0);
        } else {
            showTime.setPrice(15.0);
        }

        return showTimeRepository.save(showTime);
    }
}
