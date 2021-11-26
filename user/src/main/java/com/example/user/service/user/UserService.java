package com.example.user.service.user;

import com.example.root.dto.user.UpdateUserRequest;
import com.example.root.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean activateAdmin(String code);

    UserDto updateUser(long id, UpdateUserRequest updateUserRequest);
}
