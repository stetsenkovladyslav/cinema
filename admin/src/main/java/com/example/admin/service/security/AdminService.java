package com.example.admin.service.security;

import com.example.root.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.EntityNotFoundException;

public interface AdminService extends UserDetailsService {

    void sendMessage(User user);

    void addAdmin(long id) throws EntityNotFoundException;

    void deleteUser(long id);


}
