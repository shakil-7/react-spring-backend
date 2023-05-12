package com.example.reactspringbackend.controller;

import com.example.reactspringbackend.dto.ResponseDto;
import com.example.reactspringbackend.dto.UserDetailsDto;
import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InternalServerError;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisMobileNumber;
import com.example.reactspringbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin("*")
@RestController
public class UserInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public ResponseEntity<List<UserEntity>> getAllUserInfo() {

        List<UserEntity> allUsers = userService.getAllUsers();
        return new ResponseEntity<List<UserEntity>>(allUsers, HttpStatus.OK);
    }


    @GetMapping("/user")
    public ResponseEntity<UserDetailsDto> getUserByMobileNumber(@RequestParam String mobileNumber) throws UserNotFoundWithThisMobileNumber, InternalServerError, RegisterNewUserException {
//        System.out.println("email = " + email);
        UserDetailsDto userDetails = userService.getUserDetails(mobileNumber);
        return new ResponseEntity<UserDetailsDto>(userDetails, HttpStatus.OK);
    }

    @DeleteMapping("/user")
    @Modifying
    @Transactional
    public ResponseEntity<?> deleteUserByEmail(@RequestParam String email) throws UserNotFoundWithThisMobileNumber {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok(new ResponseDto("successfully deleted"));
    }



}
