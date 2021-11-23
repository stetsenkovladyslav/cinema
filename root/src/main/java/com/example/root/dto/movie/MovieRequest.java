package com.example.root.dto.movie;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieRequest {
    @NotBlank(message = "Movie title is mandatory")
    @Size(max = 64, message = "Movie title must be less than 64 characters")
    private String movieTitle;

    @NotBlank(message = "Movie description is mandatory")
    @Size(max = 2048, message = "Movie description must be less than 2048 characters")
    private String movieDescription;

    @NotNull(message = "The value should not be null")
    @NotEmpty(message = "Movie must have at list 1 director")
    private List<Long> directorsIds;

    @NotNull(message = "The value should not be null")
    @NotEmpty(message = "Movie must have at list 1 genre")
    private List<Long> genresIds;

    @NotNull(message = "The value should not be null")
    @NotEmpty(message = "Movie must have at list 1 country")
    private List<Long> countriesIds;

    @NotNull
    private LocalDate dateAdded;

    @NotNull
    private LocalDate dateRelease;
}
