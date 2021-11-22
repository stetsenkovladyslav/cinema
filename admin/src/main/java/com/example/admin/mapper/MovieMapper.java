package com.example.admin.mapper;

import com.example.data.dto.movie.MovieDTO;
import com.example.data.dto.movie.MovieRequest;
import com.example.data.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper extends CrudMapper<Movie, MovieDTO, MovieRequest, MovieRequest> {


    Movie dtoToMovie(MovieDTO movieDTO);
//
//    @Mapping(target = "directorsIds", source = "movie.directors", qualifiedByName = "mapEntityId")
//    @Mapping(target = "genresIds", source = "movie.genres", qualifiedByName = "mapEntityId")
//    @Mapping(target = "countriesIds", source = "movie.countries", qualifiedByName = "mapEntityId")
    MovieDTO toDTO(Movie movie);

}
