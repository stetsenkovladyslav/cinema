package com.example.user.service.auth;

import com.example.root.dto.jwt.JwtResponse;
import com.example.root.dto.user.AuthenticationRequest;
import com.example.root.dto.user.UserDto;
import com.example.root.model.UserEntity;

public interface AuthService {

    JwtResponse login(AuthenticationRequest auth);
    JwtResponse register(UserDto userDto);
    UserEntity getAuthenticatedUser();


}
