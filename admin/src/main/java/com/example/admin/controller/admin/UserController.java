package com.example.admin.controller.admin;

import com.example.admin.mapper.UserMapper;
import com.example.admin.service.admin.AdminService;
import com.example.root.model.User;
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

    private final AdminService adminService;
    private final UserMapper userMapper;

    @GetMapping(value = "/create-admin/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> createAdmin(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        adminService.addAdmin(id);
        return ResponseEntity.ok("Add admin...");
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")

    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}