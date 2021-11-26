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
    private String movieDescription;
    private List<Long> directorsIds;
    private List<Long> genresIds;
    private List<Long> countriesIds;

}
