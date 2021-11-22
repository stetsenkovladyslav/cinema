package com.example.user.controller;


import com.example.user.mapper.UserMapper;
import com.example.data.model.User;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping(value = "/create-admin/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> createAdmin(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        userService.addAdmin(id);
        return ResponseEntity.ok("Add admin...");
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")

    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}