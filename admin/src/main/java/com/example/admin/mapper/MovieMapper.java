package com.example.admin.mapper;

import com.example.admin.DTO.MovieDTO;
import com.example.admin.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDTO(Movie movie);

    Movie dtoToMovie(MovieDTO movieDTO);
}
