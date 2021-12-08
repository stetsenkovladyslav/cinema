package com.example.admin.service.security;

import com.example.root.dto.user.AuthenticationRequest;

public interface AuthService {

    String login(AuthenticationRequest auth);
}
