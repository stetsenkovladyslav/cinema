package com.example.admin.mapper;

import com.example.admin.model.BaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")

public interface BaseMapper {
    @Named("mapEntityId")
    default <E extends BaseEntity> Long mapEntityId(E entity) {
        return entity != null ? entity.getId() : null;
    }
}
