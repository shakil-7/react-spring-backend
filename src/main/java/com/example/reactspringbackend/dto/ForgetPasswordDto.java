package com.example.reactspringbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForgetPasswordDto {

    private String mobileNumber;

    private String email;

    private String token;

    private String newPassword;

    public ForgetPasswordDto(String email, String token, String mobileNumber) {
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.token = token;
    }
}
