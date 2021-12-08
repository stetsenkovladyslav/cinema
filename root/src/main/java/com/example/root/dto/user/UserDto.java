package com.example.root.dto.user;

import com.example.root.enums.Role;
import com.example.root.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "FirstName field must not be empty")
    @Size(max = 80, message = "FirstName should not be more than 80 characters")
    @NotNull(message = "The value should not be null")
    private String firstName;

    @NotBlank(message = "LastName field must not be empty")
    @Size(max = 80, message = "LastName should not be more than 80 characters")
    @NotNull(message = "The value should not be null")
    private String lastName;

    @NotBlank(message = "Username field must not be empty")
    @Size(max = 24, message = "Username should not be more than 24 characters")
    @NotNull(message = "The value should not be null")
    private String username;

    @NotBlank(message = "Password field must not be empty")
    @Size(max = 64, message = "Password should not be more than 24 characters")
    private String password;

    @NotNull(message = "The value should not be null")
    private Role role;

    private String email;

    private Image image;

}
