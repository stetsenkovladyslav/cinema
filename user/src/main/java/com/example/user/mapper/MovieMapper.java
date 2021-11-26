package com.example.user.mapper;

import com.example.root.dto.movie.MovieDTO;
import com.example.root.dto.movie.MovieRequest;
import com.example.root.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper extends CrudMapper<Movie, MovieDTO, MovieRequest, MovieRequest> {


    Movie dtoToMovie(MovieDTO movieDTO);

    MovieDTO toDTO(Movie movie);

}
