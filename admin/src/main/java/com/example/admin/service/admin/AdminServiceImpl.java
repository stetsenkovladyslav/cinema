package com.example.admin.service.admin;

import com.example.admin.mapper.UserMapper;
import com.example.admin.repository.UserRepository;
import com.example.root.model.User;
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
public class AdminServiceImpl implements AdminService{
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
