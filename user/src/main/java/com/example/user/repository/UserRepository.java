package com.example.user.repository;

import com.example.root.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Page<UserEntity> findAllByEnabledIsFalse(Pageable pageable);

    UserEntity findByCode(String code);

    @Query("SELECT DISTINCT user FROM UserEntity user WHERE user.email = ?1")
    UserEntity findByEmail(String email);

    @Query("SELECT DISTINCT user.password FROM UserEntity user WHERE user.email = ?1")
    String getPasswordByEmail(String email);

    @Query("SELECT DISTINCT user.username FROM UserEntity user WHERE user.email = ?1")
    String getUsernameByEmail(String email);
}