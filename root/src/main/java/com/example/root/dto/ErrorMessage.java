package com.example.root.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private Integer status;
    private String message;
    private Instant timestamp;
    private List<ValidationErrorMessage> validationErrors;

    @Data
    public static class ValidationErrorMessage {
        private String field;
        private String message;

        public ValidationErrorMessage(String field, String defaultMessage) {
        }
    }
}
