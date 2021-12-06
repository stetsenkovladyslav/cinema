package com.example.user.facebook;

import com.example.root.dto.user.UserDto;
import com.example.root.enums.Role;
import com.example.root.jwt.JwtUtil;
import com.example.user.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final FacebookConnectionFactory factory;

    private final BCryptPasswordEncoder encoder;

    private final AuthService authService;

    private final JwtUtil jwtUtil;

    @PostMapping
    public String register() {
        OAuth2Operations operations = factory.getOAuthOperations();
        return null;
    }

    @GetMapping(value = "/facebook")
    public String producer() {

        OAuth2Operations operations = factory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();

        params.setRedirectUri("http://localhost:8082/forwardLogin");
        params.setScope("email,public_profile");

        String url = operations.buildAuthenticateUrl(params);
        System.out.println("The URL is" + url);
        return "redirect:" + url;

    }

    @RequestMapping(value = "/forwardLogin")
    public ModelAndView prodducer(@RequestParam("code") String authorizationCode) {
        OAuth2Operations operations = factory.getOAuthOperations();
        AccessGrant accessToken = operations.exchangeForAccess(authorizationCode, "http://localhost:8082/forwardLogin",
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
//        userDto.setPassword(randomAlphabetic(8));
        userDto.setPassword(username);
        ModelAndView modelAndView = new ModelAndView("index.jsp");
        modelAndView.addObject("token", authService.register(userDto).getToken());
        return modelAndView;

    }

}
