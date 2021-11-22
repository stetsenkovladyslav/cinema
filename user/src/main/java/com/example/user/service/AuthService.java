package com.example.user.service;

import com.example.data.dto.user.UserDto;
import com.example.data.model.User;

public interface AuthService {

    User login(String username, String password);

    User register(UserDto userDto);
}
