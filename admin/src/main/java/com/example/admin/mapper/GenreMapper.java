package com.example.admin.mapper;


import com.example.root.dto.genre.GenreDTO;
import com.example.root.dto.genre.GenreRequest;
import com.example.root.model.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper extends CrudMapper<Genre, GenreDTO, GenreRequest, GenreRequest>{

    GenreDTO toDTO(Genre genre);

    Genre dtoToGenre(GenreDTO genreDTO);
}