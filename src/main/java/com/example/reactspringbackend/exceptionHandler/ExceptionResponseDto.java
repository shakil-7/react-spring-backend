package com.example.reactspringbackend.exceptionHandler;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ExceptionResponseDto {

    private HttpStatus status;
    private String message;

    private boolean isUniqueEmail = true;

    public ExceptionResponseDto(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ExceptionResponseDto(HttpStatus status, String message, boolean isUniqueEmail) {
        this.status = status;
        this.message = message;
        this.isUniqueEmail = isUniqueEmail;
    }
}
