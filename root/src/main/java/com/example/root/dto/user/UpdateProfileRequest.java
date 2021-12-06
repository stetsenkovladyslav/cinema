package com.example.root.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
public class UpdateProfileRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
