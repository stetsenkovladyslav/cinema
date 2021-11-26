package com.example.admin.service.admin;

import com.example.root.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${hostname}")
    private String hostname;
    final MailSender mailSender;

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
}
