package com.example.reactspringbackend.controller;

import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InternalServerError;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisEmail;
import com.example.reactspringbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class UserInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public ResponseEntity<List<UserEntity>> getAllUserInfo() {

        List<UserEntity> allUsers = userService.getAllUsers();

//        System.out.println("allUsers = " + allUsers);
        return new ResponseEntity<List<UserEntity>>(allUsers, HttpStatus.OK);
    }


    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUserByEmail(@RequestParam(required = false) String email) throws UserNotFoundWithThisEmail, InternalServerError, RegisterNewUserException {
//        System.out.println("email = " + email);
        UserEntity user = userService.getUserByEmail(email);
        return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
    }
}
