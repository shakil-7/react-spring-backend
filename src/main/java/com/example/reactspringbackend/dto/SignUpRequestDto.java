package com.example.reactspringbackend.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String id;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private String gender;
}
