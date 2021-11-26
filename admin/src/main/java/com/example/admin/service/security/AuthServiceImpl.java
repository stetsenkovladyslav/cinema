package com.example.admin.service.security;

import com.example.admin.mapper.UserMapper;
import com.example.admin.repository.UserRepository;
import com.example.root.dto.user.AuthenticationRequest;
import com.example.root.dto.user.UserDto;
import com.example.root.enums.Role;
import com.example.root.exception.UserAlreadyExistException;
import com.example.root.model.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;



    @Autowired
    public AuthServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder encoder,
                           AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(AuthenticationRequest auth) {
        String username = auth.getUsername();
        String password = auth.getPassword();
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username:{" + username + "} does not exist"));
        if (!encoder.matches(password, user.getPassword()))
            throw new AccessDeniedException("Incorrect password");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        return jwtUtil.generateToken(user);
    }

}
