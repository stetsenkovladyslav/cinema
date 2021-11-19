package com.example.user.service;

import com.example.user.dto.UserDto;
import com.example.user.model.User;

public interface AuthService {

    User login(String username, String password);

    User register(UserDto userDto);
}
