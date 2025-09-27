package com.cloud.movieMicroservice.showTime.infrastructure;

import com.cloud.movieMicroservice.showTime.domain.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findByMovieId(Long movieId);
}
