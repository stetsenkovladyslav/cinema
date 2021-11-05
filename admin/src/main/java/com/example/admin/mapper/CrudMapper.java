package com.example.admin.mapper;

import org.mapstruct.MappingTarget;

public interface CrudMapper<E, D, C, U> extends DTOMapper<E, D> {
    E create(C request);

    E update(@MappingTarget E entity, U request);
}
