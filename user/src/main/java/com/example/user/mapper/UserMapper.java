package com.example.user.mapper;

import com.example.root.dto.user.UserDto;
import com.example.root.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends CrudMapper<User, UserDto, UserDto, UserDto> {
    User dtoToUser(UserDto userDto);

}