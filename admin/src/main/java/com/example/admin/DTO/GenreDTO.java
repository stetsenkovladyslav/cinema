package com.example.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    @NotBlank(message = "Genre name is mandatory")
    @Size(max = 64, message = "Genre name must be less than 64 characters")
    private String genreName;
}
