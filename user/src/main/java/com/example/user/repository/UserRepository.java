package com.example.user.repository;

import com.example.root.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Page<User> findAllByEnabledIsFalse(Pageable pageable);

    User findByCode(String code);

    @Query("SELECT DISTINCT user.email FROM User user WHERE user.email = ?1")
    String findByEmail(String email);

    @Query("SELECT DISTINCT user.password FROM User user WHERE user.email = ?1")
    String getPasswordByEmail(String email);

    @Query("SELECT DISTINCT user.username FROM User user WHERE user.email = ?1")
    String getUsernameByEmail(String email);
}