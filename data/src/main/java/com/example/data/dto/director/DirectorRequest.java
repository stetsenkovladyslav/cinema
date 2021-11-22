package com.example.data.dto.director;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class DirectorRequest {
    @NotBlank(message = "Name field must not be empty")
    @Size(max = 80, message = "Name should not be more than 80 characters")
    @NotNull(message = "The value should not be null")
    private String directorName;
}
