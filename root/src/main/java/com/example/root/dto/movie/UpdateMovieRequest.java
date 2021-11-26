package com.example.root.dto.movie;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;
@Getter
@Setter
public class UpdateMovieRequest {

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
    @NotEmpty(message = "Movie must have at list 1 genre")
    private List<Long> countriesIds;


}
