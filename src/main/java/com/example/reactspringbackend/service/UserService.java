package com.example.reactspringbackend.service;


import com.example.reactspringbackend.dto.SignUpRequestDto;
import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InternalServerError;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.NotUniqueEmailException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisEmail;

import java.util.List;

public interface UserService {
    void registerNewUser(SignUpRequestDto requestDto) throws RegisterNewUserException, NotUniqueEmailException;

    List<UserEntity> getAllUsers();

    UserEntity getUserByEmail(String email) throws InternalServerError, UserNotFoundWithThisEmail, RegisterNewUserException;
}
