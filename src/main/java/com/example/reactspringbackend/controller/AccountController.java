package com.example.reactspringbackend.controller;


import com.example.reactspringbackend.dto.AddMoneyDto;
import com.example.reactspringbackend.dto.ResponseDto;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisEmail;
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
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/add_money")
    public ResponseEntity<?> addMoney(@RequestBody AddMoneyDto dto) throws UserNotFoundWithThisEmail {

        userService.addMoney(dto);
        return new ResponseEntity< ResponseDto > (new ResponseDto("success"),HttpStatus.OK);
    }
}
