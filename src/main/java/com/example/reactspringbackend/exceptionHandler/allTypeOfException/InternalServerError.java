package com.example.reactspringbackend.exceptionHandler.allTypeOfException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InternalServerError extends Throwable {
    private String message;
    public InternalServerError(String message){
        this.message = message;
    }
}
