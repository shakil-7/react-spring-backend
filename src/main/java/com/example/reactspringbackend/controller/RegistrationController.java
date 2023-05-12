package com.example.reactspringbackend.controller;

import com.example.reactspringbackend.dto.SignUpRequestDto;
import com.example.reactspringbackend.dto.ResponseDto;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.NotUniqueMobileNumberException;
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
    public ResponseEntity<ResponseDto> register(@RequestBody SignUpRequestDto requestDto) throws RegisterNewUserException,
            NotUniqueMobileNumberException {

        userService.registerNewUser(requestDto);
//        System.out.println("successfully registered");
        return ResponseEntity.ok(new ResponseDto(200, "Successfully registered"));
    }

}
