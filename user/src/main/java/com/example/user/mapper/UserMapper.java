package com.example.user.mapper;

import com.example.data.dto.user.UserDto;
import com.example.data.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User dtoToUser(UserDto userDto);

}