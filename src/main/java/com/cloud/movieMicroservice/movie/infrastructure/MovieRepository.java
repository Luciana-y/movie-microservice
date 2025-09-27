package com.cloud.movieMicroservice.movie.infrastructure;

import com.cloud.movieMicroservice.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> { }