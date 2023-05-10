package com.example.reactspringbackend.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ToClientResponse {
    private HttpStatus status;
    private String message;

    public ToClientResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
