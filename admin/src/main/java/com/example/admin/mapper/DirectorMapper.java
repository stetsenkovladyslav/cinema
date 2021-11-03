package com.example.admin.mapper;

import com.example.admin.DTO.DirectorDTO;
import com.example.admin.model.Director;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface DirectorMapper {

    DirectorDTO toDTO(Director director);

    Director dtoToDirector(DirectorDTO directorDTO);
}
