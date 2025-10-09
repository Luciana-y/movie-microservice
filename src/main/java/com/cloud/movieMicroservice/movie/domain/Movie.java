package com.cloud.movieMicroservice.movie.domain;

import com.cloud.movieMicroservice.showTime.domain.ShowTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private String genre;

    @NotBlank
    @Column(length = 1000)
    private String description;

    @NotNull
    private Integer time; // en minutos

    @NotNull
    private String ageRestriction;

    @NotNull
    private Boolean premiere; // estreno o no

    // Relación uno a muchos con ShowTime
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowTime> showTimes;
}
