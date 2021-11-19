package com.example.user.mapper;

import com.example.user.dto.UserDto;
import com.example.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User dtoToUser(UserDto userDto);

}