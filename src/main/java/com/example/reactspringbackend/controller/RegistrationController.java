package com.example.reactspringbackend.controller;

import com.example.reactspringbackend.dto.SignUpRequestDto;
import com.example.reactspringbackend.dto.ToClientResponse;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.NotUniqueEmailException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;
import com.example.reactspringbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RegistrationController {


    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<ToClientResponse> register(@RequestBody SignUpRequestDto requestDto) throws RegisterNewUserException,
                                                                            NotUniqueEmailException {

        userService.registerNewUser(requestDto);
//        System.out.println("successfully registered");
        return ResponseEntity.ok(new ToClientResponse(HttpStatus.OK, "Successfully registered"));
    }

}
