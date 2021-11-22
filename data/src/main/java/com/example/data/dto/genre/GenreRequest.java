package com.example.data.dto.genre;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GenreRequest {
    @NotNull
    @NotEmpty
    private String genreName;
}
