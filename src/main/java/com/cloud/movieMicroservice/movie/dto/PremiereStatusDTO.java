package com.cloud.movieMicroservice.movie.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PremiereStatusDTO {
    @NotNull
    private Boolean premiere;
}
