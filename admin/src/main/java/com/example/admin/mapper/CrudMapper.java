package com.example.admin.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface CrudMapper<E, D, C, U> extends DTOMapper<E, D> {
    E create(C request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E update(@MappingTarget E entity, U request);
}
