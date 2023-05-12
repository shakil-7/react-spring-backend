package com.example.reactspringbackend.exceptionHandler.allTypeOfException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserNotFoundWithThisMobileNumber extends Throwable{
    private String message;
    public UserNotFoundWithThisMobileNumber(String message) {
        this.message = message;
    }
}
