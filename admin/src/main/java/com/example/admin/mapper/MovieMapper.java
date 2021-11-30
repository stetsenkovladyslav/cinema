package com.example.admin.mapper;


import com.example.admin.service.director.DirectorService;
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
    protected DirectorService directorService;

    @Override
    @Mapping(target = "directors", expression = "java(directorService.getAllByIds(request.getDirectorsIds()))")
    @Mapping(target = "genre", source = "request.genre")
    @Mapping(target = "country", source = "request.country")
    public abstract Movie create(MovieRequest request);

    @Override
    @Mapping(target = "directors", expression = "java(directorService.getAllByIds(request.getDirectorsIds()))")
    @Mapping(target = "movieDescription", source = "request.movieDescription")
    @Mapping(target = "genre", source = "request.genre")
    @Mapping(target = "country", source = "request.country")

    public abstract Movie update(Movie entity, UpdateMovieRequest request);

}
