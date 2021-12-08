package com.example.admin.mapper;

import com.example.root.dto.user.UserDto;
import com.example.root.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserEntity user);

    UserEntity dtoToUser(UserDto userDto);

}