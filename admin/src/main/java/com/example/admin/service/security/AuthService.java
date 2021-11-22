package com.example.admin.service.security;

import com.example.root.dto.user.UserDto;
import com.example.root.model.User;

public interface AuthService {

    User login(String username, String password);
}
