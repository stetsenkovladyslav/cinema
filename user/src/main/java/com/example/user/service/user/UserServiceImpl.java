package com.example.user.service.user;

import com.example.root.enums.Role;
import com.example.root.model.UserEntity;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException
                        ("UserEntity with username:{" + username + "} does not exist"));
    }

    @Override
    public boolean activateAdmin(String code) {
        UserEntity user = userRepository.findByCode(code);
        if (user == null) {
            return false;
        }
        user.setCode(null);
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return true;
    }


}