package com.example.user.service.auth;

import com.example.root.dto.user.AuthenticationRequest;
import com.example.root.dto.user.UserDto;
import com.example.root.model.User;
import org.springframework.lang.NonNull;

public interface AuthService {

    String login(AuthenticationRequest auth);
    User register(UserDto userDto);

    @NonNull
    User getAuthenticatedUser();
}
