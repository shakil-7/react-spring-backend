package com.example.reactspringbackend.exceptionHandler.allTypeOfException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterNewUserException extends Throwable {
    private String message;

    public RegisterNewUserException(String message) {
        this.message = message;
    }
}
