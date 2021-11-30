package com.example.root.dto.movie;

import com.example.root.enums.Country;
import com.example.root.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateMovieRequest {
    private String movieDescription;
    private List<Long> directorsIds;
    private Genre genre;
    private Country country;


}
