package com.example.reactspringbackend.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto {
    private int status;
    private String message;


    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
