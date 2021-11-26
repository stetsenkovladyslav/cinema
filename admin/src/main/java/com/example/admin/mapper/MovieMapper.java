package com.example.admin.mapper;

import com.example.admin.service.country.CountryService;
import com.example.admin.service.director.DirectorService;
import com.example.admin.service.genre.GenreService;
import com.example.root.dto.movie.MovieDTO;
import com.example.root.dto.movie.MovieRequest;
import com.example.root.dto.movie.UpdateMovieRequest;
import com.example.root.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MovieMapper implements CrudMapper<Movie, MovieDTO, MovieRequest, UpdateMovieRequest> {

    @Autowired
    protected GenreService genreService;

    @Autowired
    protected DirectorService directorService;

    @Autowired
    protected CountryService countryService;

    @Override
    @Mapping(target = "directors", expression = "java(directorService.getAllByIds(request.getDirectorsIds()))")
    @Mapping(target = "genres", expression = "java(genreService.getAllByIds(request.getGenresIds()))")
    @Mapping(target = "countries", expression = "java(countryService.getAllByIds(request.getCountriesIds()))")
    public abstract Movie create(MovieRequest request);

    @Override
    @Mapping(target = "directors", expression = "java(directorService.getAllByIds(request.getDirectorsIds()))")
    @Mapping(target = "genres", expression = "java(genreService.getAllByIds(request.getGenresIds()))")
    @Mapping(target = "countries", expression = "java(countryService.getAllByIds(request.getCountriesIds()))")
    @Mapping(target = "movieDescription", source = "request.movieDescription")
    public abstract Movie update(Movie entity, UpdateMovieRequest request);

}
