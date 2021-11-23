package com.example.user.service.user;

import com.example.root.dto.country.CountryDTO;
import com.example.root.dto.country.CountryRequest;
import com.example.root.dto.user.UpdateUserRequest;
import com.example.root.dto.user.UserDto;
import com.example.root.enums.Role;
import com.example.root.model.Country;
import com.example.root.model.User;
import com.example.user.mapper.UserMapper;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Value("${hostname}")
    private String hostname;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException
                        ("User with username:{" + username + "} does not exist"));
    }

    @Override
    public boolean activateAdmin(String code) {
        User user = userRepository.findByCode(code);
        if (user == null) {
            return false;
        }
        user.setCode(null);
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto updateUser(long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id:{" + id + "} does not exist"));
        return userMapper.mapToDTO(userRepository.save(userMapper.update(user, updateUserRequest)));
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

}