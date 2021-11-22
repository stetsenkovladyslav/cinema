package com.example.user.service.auth;

import com.example.root.dto.user.UserDto;
import com.example.root.model.User;

public interface AuthService {

    User login(String username, String password);

    User register(UserDto userDto);
}
