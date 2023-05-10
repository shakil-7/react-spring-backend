package com.example.reactspringbackend.service;


import com.example.reactspringbackend.dto.SignUpRequestDto;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.NotUniqueEmailException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;

public interface UserService {
    void registerNewUser(SignUpRequestDto requestDto) throws RegisterNewUserException, NotUniqueEmailException;
}
