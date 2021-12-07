package com.example.user.service.auth;

import antlr.TokenStreamRewriteEngine;
import com.example.root.dto.jwt.JwtResponse;
import com.example.root.dto.user.AuthenticationRequest;
import com.example.root.dto.user.UserDto;
import com.example.root.model.User;
import org.springframework.lang.NonNull;

public interface AuthService {

    JwtResponse login(AuthenticationRequest auth);
    JwtResponse register(UserDto userDto);

    @NonNull
    User getAuthenticatedUser();


}
