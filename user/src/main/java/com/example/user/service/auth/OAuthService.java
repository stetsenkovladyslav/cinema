package com.example.user.service.auth;

import com.example.root.dto.jwt.JwtResponse;
import org.springframework.web.servlet.view.RedirectView;

public interface OAuthService {

    RedirectView getUrl();

    JwtResponse token(String authorizationCode);
}
