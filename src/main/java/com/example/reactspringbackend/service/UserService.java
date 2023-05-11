package com.example.reactspringbackend.service;


import com.example.reactspringbackend.dto.AddMoneyDto;
import com.example.reactspringbackend.dto.LoginDto;
import com.example.reactspringbackend.dto.SignUpRequestDto;
import com.example.reactspringbackend.dto.UserDetailsDto;
import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InternalServerError;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.NotUniqueEmailException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisEmail;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    void registerNewUser(SignUpRequestDto requestDto) throws RegisterNewUserException, NotUniqueEmailException;

    List<UserEntity> getAllUsers();


    void deleteUserByEmail(String email) throws UserNotFoundWithThisEmail;

    boolean login(LoginDto loginDto) throws UserNotFoundWithThisEmail;

    UserDetailsDto getUserDetails(String email) throws InternalServerError, UserNotFoundWithThisEmail;

    void addMoney(AddMoneyDto dto) throws UserNotFoundWithThisEmail;
}
