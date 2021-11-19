package com.example.user.service;


import com.example.user.dto.UserDto;
import com.example.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    void sendMessage(User user);

    boolean activateAdmin(String code);

    void addAdmin(long id) throws EntityNotFoundException;

    void deleteUser(long id);
}
