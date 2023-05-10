package com.example.reactspringbackend.exceptionHandler.allTypeOfException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotUniqueEmailException extends Throwable {
    private String message;

    public NotUniqueEmailException(String message) {
        this.message = message;
    }
}
