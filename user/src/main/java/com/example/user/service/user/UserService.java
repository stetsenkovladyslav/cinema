package com.example.user.service.user;

import com.example.root.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.EntityNotFoundException;

public interface UserService extends UserDetailsService {

    boolean activateAdmin(String code);
}
