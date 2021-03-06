package com.example.admin.service.admin;

import com.example.admin.repository.UserRepository;
import com.example.root.enums.Role;
import com.example.root.exception.UserRoleAdminException;
import com.example.root.model.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final EmailService emailService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException
                        ("UserEntity with username:{" + username + "} does not exist"));
    }

    @Override
    public void addAdmin(long id) throws EntityNotFoundException, UserRoleAdminException {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException
                ("UserEntity with id:{" + id + "} does not exist"));
        if (user.getRole() == Role.USER) {
            user.setCode(UUID.randomUUID().toString());
            emailService.sendMessage(user);
            userRepository.save(user);
        }else
            throw new UserRoleAdminException("UserEntity already has ADMIN role");
    }

    @Override
    public void deleteUser(long id) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException
                ("UserEntity with id:{" + id + "} does not exist"));
        userRepository.deleteById(id);
    }

}
