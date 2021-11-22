package com.example.admin.mapper;

import com.example.root.dto.user.UserDto;
import com.example.root.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User dtoToUser(UserDto userDto);

}