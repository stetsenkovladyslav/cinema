package com.example.user.service;

import com.example.user.mapper.UserMapper;
import com.example.data.enums.Role;
import com.example.data.model.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final MailSender mailSender;
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
    public void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s %s! \n" +
                    "Please, visit next link: http://%s/activate/%s",
                    user.getFirstName(),
                    user.getLastName(),
                    hostname,
                    user.getCode()
            );
            mailSender.send(user.getEmail(), "Activate code", message);
        }
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
    public void addAdmin(long id) throws EntityNotFoundException {
        User user = userRepository.getById(id);
        user.setCode(UUID.randomUUID().toString());
        userRepository.save(user);
        sendMessage(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }


}