package com.cloud.movieMicroservice.movie.domain;

import com.cloud.movieMicroservice.movie.infrastructure.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            movie.setName(updatedMovie.getName());
            movie.setGenre(updatedMovie.getGenre());
            movie.setDescription(updatedMovie.getDescription());
            movie.setTime(updatedMovie.getTime());
            movie.setAgeRestriction(updatedMovie.getAgeRestriction());
            movie.setPremiere(updatedMovie.getPremiere());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie updatePremiereStatus(Long id, Boolean isPremiere) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setPremiere(isPremiere);
                    return movieRepository.save(movie);
                })
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
