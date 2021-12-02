package com.example.user.mapper;

import com.example.root.dto.movie.MovieDTO;
import com.example.root.dto.movie.MovieRequest;
import com.example.root.enums.Country;
import com.example.root.enums.Genre;
import com.example.root.model.Director;
import com.example.root.model.Movie;
import com.example.root.model.Rate;
import com.example.user.dto.MoviePreviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper extends CrudMapper<Movie, MovieDTO, MovieRequest, MovieRequest> {


    Movie dtoToMovie(MovieDTO movieDTO);

    MovieDTO toDTO(Movie movie);

    MoviePreviewDTO mapToMovieSearchDTO(Movie movie);

}
