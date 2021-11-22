package com.example.admin.mapper;

import com.example.data.dto.director.DirectorDTO;
import com.example.data.dto.director.DirectorRequest;
import com.example.data.model.Director;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface DirectorMapper extends CrudMapper<Director, DirectorDTO, DirectorRequest, DirectorRequest>{

    DirectorDTO toDTO(Director director);

    Director dtoToDirector(DirectorDTO directorDTO);
}
