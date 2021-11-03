package com.example.admin.mapper;


import com.example.admin.DTO.GenreDTO;
import com.example.admin.model.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDTO(Genre genre);

    Genre dtoToGenre(GenreDTO genreDTO);
}