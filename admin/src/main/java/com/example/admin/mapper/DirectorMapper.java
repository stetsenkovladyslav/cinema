package com.example.admin.mapper;

import com.example.admin.dto.director.DirectorDTO;
import com.example.admin.dto.director.DirectorRequest;
import com.example.admin.model.Director;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface DirectorMapper extends CrudMapper<Director, DirectorDTO, DirectorRequest, DirectorRequest>{

    DirectorDTO toDTO(Director director);

    Director dtoToDirector(DirectorDTO directorDTO);
}
