package com.example.user.service.auth;

import com.example.root.dto.jwt.JwtResponse;
import com.example.root.dto.user.AuthenticationRequest;
import com.example.root.dto.user.FacebookAuthenticationRequest;
import com.example.root.dto.user.UserDto;
import com.example.root.enums.Role;
import com.example.root.exception.AuthenticationException;
import com.example.root.exception.UserAlreadyExistException;
import com.example.root.jwt.JwtUtil;
import com.example.root.model.User;
import com.example.user.mapper.UserMapper;
import com.example.user.repository.UserRepository;
import com.example.user.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder encoder,
                           UserMapper userMapper, @Lazy AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil, UserService userService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Nullable
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @NonNull
    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null)
            throw new AuthenticationException("Authorization exception");
        Object principal = authentication.getPrincipal();
        if (principal == null)
            throw new AuthenticationException("Authorization exception");
        UserDetails user = (UserDetails) principal;
        return (User) userService.loadUserByUsername(user.getUsername());
    }

    @Override
    public JwtResponse login(AuthenticationRequest auth) {
        String username = auth.getUsername();
        String password = auth.getPassword();
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username:{" + username + "} does not exist"));
        if (!encoder.matches(password, user.getPassword()))
            throw new AccessDeniedException("Incorrect password");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        return new JwtResponse(jwtUtil.generateToken(user));
    }



    @Override
    public JwtResponse register(UserDto userDto) throws UserAlreadyExistException {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("User with this username already exist");
        }
        User newUser = userMapper.dtoToUser(userDto);
        if (newUser.getRole() == Role.ADMIN) {
            newUser.setRole(Role.USER);
            newUser.setEnabled(true);
        }
        newUser.setPassword(encoder.encode(userDto.getPassword()));
        newUser.setLocked(false);
        newUser.setEnabled(true);
        userRepository.save(newUser);
        return new JwtResponse(jwtUtil.generateToken(newUser));

    }

}
