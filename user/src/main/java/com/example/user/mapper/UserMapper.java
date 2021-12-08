package com.example.user.mapper;

import com.example.root.dto.user.UserDto;
import com.example.root.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends CrudMapper<UserEntity, UserDto, UserDto, UserDto> {
    UserEntity dtoToUser(UserDto userDto);

}