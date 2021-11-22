package com.example.user.mapper;

public interface DTOMapper<E, D> {
    D mapToDTO(E entity);
}

