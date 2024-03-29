package com.example.reactspringbackend.exceptionHandler.allTypeOfException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RegisterNewUserException extends Throwable {
    private String message;

    public RegisterNewUserException(String message) {
        this.message = message;
    }
}
