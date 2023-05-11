package com.example.reactspringbackend.exceptionHandler.allTypeOfException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserNotFoundWithThisEmail extends Throwable{
    private String message;
    public UserNotFoundWithThisEmail(String message) {
        this.message = message;
    }
}
