package com.example.admin.controller.admin;

import com.example.admin.service.security.AuthService;
import com.example.root.dto.jwt.JwtResponse;
import com.example.root.dto.user.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public JwtResponse createAuthenticationToken(@RequestBody @Valid AuthenticationRequest auth) {
        return new JwtResponse(authService.login(auth));
    }

}