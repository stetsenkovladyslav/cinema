package com.example.admin.service.admin;

import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.EntityNotFoundException;

public interface AdminService extends UserDetailsService {

    void addAdmin(long id) throws EntityNotFoundException, UserRoleAdminException;

    void deleteUser(long id);


}
