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
    private final UserService adminService;


    @PostMapping(value = "/signin/native")
    public JwtResponse createAuthenticationToken(@RequestBody @Valid AuthenticationRequest auth) {
        return authService.login(auth);
    }

    @PostMapping(value = "/signup/native")
    public JwtResponse registerUser(@RequestBody @Valid UserDto userDto) {
        return authService.register(userDto);
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activate(Model model, @PathVariable String code) {
        boolean isActivated = adminService.activateAdmin(code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("messageType", "danger");
        }
        return ResponseEntity.ok("ADMIN successfully activated");
    }






}