package com.example.reactspringbackend.controller;

import com.example.reactspringbackend.dto.LoginDto;
import com.example.reactspringbackend.dto.ResponseDto;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InvalidPasswordException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisMobileNumber;
import com.example.reactspringbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws UserNotFoundWithThisMobileNumber, InvalidPasswordException {

        boolean canLogin = userService.login(loginDto);

//        System.out.println("loginDto = " + loginDto);


        if(canLogin) return ResponseEntity.ok(new ResponseDto("Successfully logged in"));
        else throw new InvalidPasswordException("Invalid password");
    }
}
