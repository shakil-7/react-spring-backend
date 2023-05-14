package com.example.reactspringbackend.controller;

import com.example.reactspringbackend.dto.ResponseDto;
import com.example.reactspringbackend.dto.UserDetailsDto;
import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InternalServerError;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisMobileNumber;
import com.example.reactspringbackend.service.JwtService;
import com.example.reactspringbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin("*")
@RestController
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/all-user")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserEntity>> getAllUserInfo(HttpServletRequest request) {

        System.out.println("request.getHeader(\"Authorization\") = " + request.getHeader("Authorization"));
        
        List<UserEntity> allUsers = userService.getAllUsers();
        return new ResponseEntity<List<UserEntity>>(allUsers, HttpStatus.OK);
    }


    @GetMapping("/user")
    public ResponseEntity<?> getUserByMobileNumber(@RequestParam String mobileNumber, HttpServletRequest request) throws UserNotFoundWithThisMobileNumber, InternalServerError, RegisterNewUserException {
//        System.out.println("email = " + email);

//        System.out.println("mobileNumber = " + mobileNumber);
        UserDetailsDto userDetails = userService.getUserDetails(mobileNumber);
        // check if the requested profile and the person who requested it is same

        String token = request.getHeader("Authorization");
        token = token.substring(7);
//        System.out.println("token = " + token);
        String username = jwtService.extractUsername(token);
//        System.out.println("username = " + username);
//        System.out.println("mobileNumber = " + mobileNumber);
//        System.out.println("\n\n\n");
        if(username.equals(mobileNumber)){
            return new ResponseEntity<UserDetailsDto>(userDetails, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(new ResponseDto("You are not authorized to access this page"));
    }

    @DeleteMapping("/user")
    @Modifying
    @Transactional
    public ResponseEntity<?> deleteUserByMobileNumber(@RequestParam String mobileNumber) throws UserNotFoundWithThisMobileNumber {
        userService.deleteUserByMobileNumber(mobileNumber);
        return ResponseEntity.ok(new ResponseDto("successfully deleted"));
    }



}
