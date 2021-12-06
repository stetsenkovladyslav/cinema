package com.example.user.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean activateAdmin(String code);

}
