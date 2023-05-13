package com.example.reactspringbackend.controller;

import com.example.reactspringbackend.dto.JwtTokenDto;
import com.example.reactspringbackend.dto.LoginDto;
import com.example.reactspringbackend.dto.ResponseDto;
import com.example.reactspringbackend.dto.TransactionDetailsDto;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InvalidPasswordException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisMobileNumber;
import com.example.reactspringbackend.service.JwtService;
import com.example.reactspringbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

//    @PostMapping("/user/login")
//    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws UserNotFoundWithThisMobileNumber, InvalidPasswordException {
//
//        boolean canLogin = userService.login(loginDto);
//
////        System.out.println("loginDto = " + loginDto);
//
//
//        if(canLogin) {
//
//            return ResponseEntity.ok(new ResponseDto("Successfully logged in"));
//        }
//        else throw new InvalidPasswordException("Invalid password");
//    }

    @PostMapping("/user/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginDto loginDto) throws UserNotFoundWithThisMobileNumber, InvalidPasswordException {

        boolean canLogin = userService.login(loginDto);

//        System.out.println("loginDto = " + loginDto);


        if(canLogin) {
            JwtTokenDto jwtTokenDto = new JwtTokenDto(
                    jwtService.generateToken(loginDto.getMobileNumber())
            );
            return new ResponseEntity<JwtTokenDto> (jwtTokenDto, HttpStatus.OK);
        }
        else throw new InvalidPasswordException("Invalid password");
    }
}
