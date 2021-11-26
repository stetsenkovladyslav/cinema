package com.example.user.controller;

import com.example.root.dto.user.UpdateUserRequest;
import com.example.root.dto.user.UserDto;
import com.example.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping(value = "/{id}")
    @Secured("ROLE_USER")
    UserDto update(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }

}