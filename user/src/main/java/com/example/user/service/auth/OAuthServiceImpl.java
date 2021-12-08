package com.example.user.service.auth;

import com.example.root.dto.jwt.JwtResponse;
import com.example.root.dto.user.UserDto;
import com.example.root.enums.Role;
import com.example.root.jwt.JwtUtil;
import com.example.user.mapper.UserMapper;
import com.example.user.repository.UserRepository;
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

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    private final FacebookConnectionFactory factory;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;


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
        String email = user.getEmail();
        if (userRepository.findByEmail(email) == null) {
            UserDto userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setRole(Role.USER);
            userDto.setUsername(email);
            userDto.setPassword(randomAlphanumeric(9));
            return new JwtResponse(authService.register(userDto).getToken());
        } else {
            var facebookUser = userRepository.findByEmail(email);
            userMapper.mapToDTO(facebookUser);
            return new JwtResponse(jwtUtil.generateToken(facebookUser));
        }
    }
}