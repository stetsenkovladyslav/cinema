package com.example.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    @NotBlank(message = "Movie title is mandatory")
    @Size(max = 64, message = "Movie title must be less than 64 characters")
    private String movieTitle;

    @NotBlank(message = "Movie description is mandatory")
    @Size(max = 2048, message = "Movie description must be less than 2048 characters")
    private String movieDescription;

    @NotNull(message = "The value should not be null")
    @NotEmpty(message = "Movie must have at list 1 director")
    private List<Long> directorsId;

    @NotNull(message = "The value should not be null")
    @NotEmpty(message = "Movie must have at list 1 genre")
    @NotBlank(message = "Movie description is mandatory")
    private List<Long> genresIds;

    @Size(max = 128, message = "Movie photo name must be less than 128 characters")
    private List<String> image;

    @Size(max = 128, message = "Movie video name must be less than 128 characters")
    private List<String> video;

    @NotNull(message = "The value should not be null")
    @NotEmpty(message = "Movie must have at list 1 country")
    @NotBlank(message = "Movie description is mandatory")
    private List<Long> countriesIds;

    @NotNull
    private LocalDate dateAdded;

    @NotNull
    private LocalDate dateRelease;

}
