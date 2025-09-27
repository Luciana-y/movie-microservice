package com.cloud.movieMicroservice.showTime.application;

import com.cloud.movieMicroservice.showTime.domain.ShowTime;
import com.cloud.movieMicroservice.showTime.domain.ShowTimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/{movieId}/showtimes")
public class ShowTimeController {

    private final ShowTimeService showTimeService;

    public ShowTimeController(ShowTimeService showTimeService) {
        this.showTimeService = showTimeService;
    }
    @GetMapping
    public List<ShowTime> getShowTimesByMovie(@PathVariable Long movieId) {
        return showTimeService.getShowTimesByMovie(movieId);
    }

    @PostMapping
    public ResponseEntity<ShowTime> createShowTime(@PathVariable Long movieId, @RequestBody ShowTime showTime) {

        ShowTime createdShowTime = showTimeService.createShowTime(movieId, showTime);

        return ResponseEntity.ok(createdShowTime);
    }
}
