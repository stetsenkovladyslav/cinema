package com.example.user.controller;

import com.example.root.dto.jwt.JwtResponse;
import com.example.user.service.auth.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;

    @GetMapping(value = "/facebook")
    public RedirectView facebook() {
        return oAuthService.getUrl();
    }

    @RequestMapping(value = "/token")
    @ResponseBody
    public JwtResponse toket(@RequestParam("code") String authorizationCode) {
        return oAuthService.token(authorizationCode);
    }



}


