package com.example.reactspringbackend.controller;

import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class GetUserInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public List<UserEntity> getAllUserInfo() {

        List<UserEntity> allUsers = userService.getAllUsers();

        System.out.println("allUsers = " + allUsers);
        return allUsers;
    }
}
