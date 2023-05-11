package com.example.reactspringbackend.exceptionHandler.allTypeOfException;

import lombok.Data;

@Data
public class InvalidPasswordException extends Throwable {
    private String message;

    public InvalidPasswordException(String message) {
        this.message = message;
    }
}
