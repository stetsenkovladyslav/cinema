package com.example.user.mapper;

import com.example.root.dto.user.ProfileDto;
import com.example.root.dto.user.UpdateProfileRequest;
import com.example.root.dto.user.UserDto;
import com.example.root.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper extends CrudMapper<User, ProfileDto, UpdateProfileRequest, UpdateProfileRequest> {
}