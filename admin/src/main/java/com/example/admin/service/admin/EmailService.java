package com.example.admin.service.admin;

import com.example.root.model.User;

public interface EmailService {
    void sendMessage(User user);
}
