package com.example.root.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UpdateUserRequest {

    @NotBlank(message = "FirstName field must not be empty")
    @Size(max = 80, message = "FirstName should not be more than 80 characters")
    @NotNull(message = "The value should not be null")
    private String firstName;

    @NotBlank(message = "LastName field must not be empty")
    @Size(max = 80, message = "LastName should not be more than 80 characters")
    @NotNull(message = "The value should not be null")
    private String lastName;

    @NotBlank(message = "Email field must not be empty")
    @NotNull(message = "The value should not be null")
    private String email;
}
