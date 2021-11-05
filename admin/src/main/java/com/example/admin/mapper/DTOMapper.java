package com.example.admin.mapper;

public interface DTOMapper<E, D> {
    D mapToDTO(E entity);
}

