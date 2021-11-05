package com.example.admin.mapper;


import com.example.admin.dto.genre.GenreDTO;
import com.example.admin.dto.genre.GenreRequest;
import com.example.admin.model.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper extends CrudMapper<Genre, GenreDTO, GenreRequest, GenreRequest>{

    GenreDTO toDTO(Genre genre);

    Genre dtoToGenre(GenreDTO genreDTO);
}