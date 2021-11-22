package com.example.user.controller;

import com.example.root.dto.user.AuthenticationRequest;
import com.example.root.dto.user.UserDto;
import com.example.root.model.User;
import com.example.user.security.JwtUtil;
import com.example.user.service.auth.AuthService;
import com.example.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService adminService;


    @PostMapping(
            value = "/login")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest auth) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        final UserDetails userDetails = authService.login(auth.getUsername(), auth.getPassword());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping(
            value = "/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDto userDto) {
        User newUser = authService.register(userDto);
        return ResponseEntity.ok(jwtUtil.generateToken(newUser));
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