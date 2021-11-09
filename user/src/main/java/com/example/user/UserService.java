package com.example.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User login(String username, String password);

    User register(UserDto userDto);

    void approveUserById(long id);

    Page<User> getAllNotEnabled(Pageable pageable);

    User getNotEnabledById(long id);

    User createAdmin();

}
