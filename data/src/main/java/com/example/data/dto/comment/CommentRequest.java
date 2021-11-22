package com.example.data.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentRequest {
    @NotBlank(message = "Comment message is mandatory")
    @Size(max = 2048, message = "Comment must be less than 2048 characters")
    private String comment;
}
