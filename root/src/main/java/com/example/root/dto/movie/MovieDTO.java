package com.example.root.dto.movie;

import com.example.root.enums.Country;
import com.example.root.enums.Genre;
import com.example.root.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotBlank
    @NotNull
    Long id;
    @NotBlank(message = "Movie title is mandatory")
    @Size(max = 64, message = "Movie title must be less than 64 characters")
    private String movieTitle;

    @NotBlank(message = "Movie description is mandatory")
    @Size(max = 2048, message = "Movie description must be less than 2048 characters")
    private String movieDescription;

    @NotNull(message = "The value should not be null")
    @NotEmpty(message = "Movie must have at list 1 director")
    private List<Director> directors;

    private Genre genre;

    private Country country;

    @Size(max = 128, message = "Movie photo name must be less than 128 characters")
    private List<String> image;

    @Size(max = 128, message = "Movie video name must be less than 128 characters")
    private List<String> video;

    @NotNull
    private Rate rate;

    private List<Comment> comments;
    @NotNull
    private LocalDate dateAdded;

    @NotNull
    private LocalDate dateRelease;



}
