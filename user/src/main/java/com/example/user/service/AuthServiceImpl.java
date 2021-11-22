package com.example.user.service;

import com.example.data.dto.user.UserDto;
import com.example.user.exception.UserAlreadyExistException;
import com.example.user.mapper.UserMapper;
import com.example.data.enums.Role;
import com.example.data.model.User;
import com.example.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper userMapper;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder encoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }


    @Override
    public User login(String username, String password) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username:{" + username + "} does not exist"));
        if (!encoder.matches(password, user.getPassword()))
            throw new AccessDeniedException("Incorrect password");
        return user;
    }

    @Override
    public User register(UserDto userDto) throws UserAlreadyExistException {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("User with thi1s username already exist");
        }
        User newUser = userMapper.dtoToUser(userDto);
        if(newUser.getRole() == Role.ADMIN){
            newUser.setRole(Role.USER);
            newUser.setEnabled(true);
        }
        newUser.setPassword(encoder.encode(userDto.getPassword()));
        newUser.setLocked(false);
        newUser.setEnabled(true);
        return userRepository.save(newUser);
    }


}
