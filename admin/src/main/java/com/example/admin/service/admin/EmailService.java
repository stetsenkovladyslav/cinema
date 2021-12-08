package com.example.admin.service.admin;

import com.example.root.model.UserEntity;

public interface EmailService {
    void sendMessage(UserEntity user);
}
