package com.example.root.dto.user;

import com.example.root.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Image image;
}
