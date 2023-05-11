package com.example.reactspringbackend.exceptionHandler;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ExceptionResponseDto {

    private int status;
    private String message;

    private boolean isUniqueEmail = true;


    public ExceptionResponseDto(int status, String message, boolean isUniqueEmail) {
        this.status = status;
        this.message = message;
        this.isUniqueEmail = isUniqueEmail;
    }

    public ExceptionResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
