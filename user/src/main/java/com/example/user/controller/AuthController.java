package com.example.user.controller;

import com.example.root.dto.jwt.JwtResponse;
import com.example.root.dto.user.AuthenticationRequest;
import com.example.root.dto.user.UserDto;
import com.example.root.jwt.JwtUtil;
import com.example.root.model.User;
import com.example.user.service.auth.AuthService;
import com.example.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;


    @PostMapping(value = "/signin")
    public JwtResponse createAuthenticationToken(@RequestBody @Valid AuthenticationRequest auth) {
        return authService.login(auth);
    }

    @PostMapping(value = "/signup")
    public JwtResponse registerUser(@RequestBody @Valid UserDto userDto) {
        return authService.register(userDto);
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code) {
        userService.activateAdmin(code);
        return "ADMIN successfully activated";
    }

}