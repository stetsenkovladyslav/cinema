package com.example.admin.mapper;

import com.example.admin.dto.movie.MovieDTO;
import com.example.admin.dto.movie.MovieRequest;
import com.example.admin.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper extends CrudMapper<Movie, MovieDTO, MovieRequest, MovieRequest> {

    MovieDTO toDTO(Movie movie);

    Movie dtoToMovie(MovieDTO movieDTO);

//    default MovieDTO mapToCreateMovie(Movie movie) {
//        if (movie == null) {
//            return null;
//        }
//        MovieDTO movieDTO = new MovieDTO();
//        movieDTO.setGenresIds(movie.getGenres());
//        movieDTO.setDirectorsId(movie.getDirectors());
//        movieDTO.setCountriesIds(movie.getCountries());
//        return movieDTO;
//    }
}
