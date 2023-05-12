package com.example.reactspringbackend.exceptionHandler.allTypeOfException;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsufficientBalanceException extends Throwable {
    private String message;

    public InsufficientBalanceException(String message){
        this.message = message;
    }
}
