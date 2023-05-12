package com.example.reactspringbackend.service;


import com.example.reactspringbackend.dto.*;
import com.example.reactspringbackend.entity.TransactionEntity;
import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.*;

import java.util.List;

public interface UserService {
    void registerNewUser(SignUpRequestDto requestDto) throws RegisterNewUserException, NotUniqueMobileNumberException;

    List<UserEntity> getAllUsers();


    void deleteUserByEmail(String email) throws UserNotFoundWithThisMobileNumber;

    boolean login(LoginDto loginDto) throws UserNotFoundWithThisMobileNumber;

    UserDetailsDto getUserDetails(String mobileNumber) throws InternalServerError, UserNotFoundWithThisMobileNumber;

    void addMoney(AddMoneyDto dto) throws UserNotFoundWithThisMobileNumber;

    void sendMoney(MoneyTransferDto dto) throws UserNotFoundWithThisMobileNumber, InsufficientBalanceException;

    void deleteUserByMobileNumber(String mobileNumber) throws UserNotFoundWithThisMobileNumber;

    List<TransactionEntity> getTransaction(String mobileNumber);
}
