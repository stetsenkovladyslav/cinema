package com.example.user.dto;

import com.example.root.enums.Country;
import com.example.root.enums.Genre;
import com.example.root.model.Director;
import com.example.root.model.Rate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePreviewDTO {

    private String movieTitle;
    private Genre genre;
    private Country country;
    private LocalDate dateAdded;
    private LocalDate dateRelease;

}
