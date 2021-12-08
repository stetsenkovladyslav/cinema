package com.example.admin.repository;

import com.example.root.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Page<UserEntity> findAllByEnabledIsFalse(Pageable pageable);

    UserEntity findByCode(String code);

}