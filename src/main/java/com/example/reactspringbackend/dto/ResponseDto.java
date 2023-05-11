package com.example.reactspringbackend.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto {
    private HttpStatus status;
    private String message;


    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
