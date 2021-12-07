package com.example.user.service.auth;

import com.example.root.dto.jwt.JwtResponse;
import com.example.root.dto.user.UserDto;
import com.example.root.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    private final FacebookConnectionFactory factory;
    private final AuthService authService;

    @Override
    public RedirectView getUrl() {
        OAuth2Operations operations = factory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8082/token");
        params.setScope("email,public_profile");
        String url = operations.buildAuthenticateUrl(params);
        return new RedirectView(url);
    }

    @Override
    public JwtResponse token(String authorizationCode) {
        OAuth2Operations operations = factory.getOAuthOperations();
        AccessGrant accessToken = operations.exchangeForAccess(authorizationCode, "http://localhost:8082/token",
                null);
        Connection<Facebook> connection = factory.createConnection(accessToken);
        Facebook facebook = connection.getApi();
        String[] fields = {"email", "first_name", "last_name"};
        User user = facebook.fetchObject("me", User.class, fields);
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        String username = (user.getFirstName().charAt(0) + user.getLastName()).toLowerCase(Locale.ROOT);
        userDto.setUsername(username);
        userDto.setRole(Role.USER);
        userDto.setPassword(randomAlphabetic(8));
        return new JwtResponse(authService.register(userDto).getToken());

    }
}
