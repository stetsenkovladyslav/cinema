package com.example.admin.controller.admin;

import com.example.admin.service.admin.AdminService;
import com.example.root.exception.UserRoleAdminException;
import com.example.root.model.UserEntity;
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

    @GetMapping(value = "/create-admin/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> createAdmin(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long userId
    ) throws UserRoleAdminException {
        adminService.addAdmin(userId);
        return ResponseEntity.ok("Add admin...");
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")

    public ResponseEntity<UserEntity> deleteUser(@PathVariable long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}