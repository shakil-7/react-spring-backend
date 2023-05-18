package com.example.reactspringbackend.controller;

import com.example.reactspringbackend.dto.*;
import com.example.reactspringbackend.entity.ResetPasswordEntity;
import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InvalidPasswordException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisMobileNumber;
import com.example.reactspringbackend.repository.ResetPasswordRepo;
import com.example.reactspringbackend.repository.UserRepo;
import com.example.reactspringbackend.service.EmailSenderService;
import com.example.reactspringbackend.service.JwtService;
import com.example.reactspringbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private EmailSenderService emailSenderService;


    @Autowired
    private ResetPasswordRepo resetPasswordRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/user/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginDto loginDto) throws UserNotFoundWithThisMobileNumber, InvalidPasswordException {
        boolean canLogin = userService.login(loginDto);
        if(canLogin) {
            JwtTokenDto jwtTokenDto = new JwtTokenDto(
                    jwtService.generateToken(loginDto.getMobileNumber())
            );
            return new ResponseEntity<JwtTokenDto> (jwtTokenDto, HttpStatus.OK);
        }
        else throw new InvalidPasswordException("Invalid password");
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<ForgetPasswordDto> forgotPassword(@RequestBody ForgetPasswordDto dto, HttpServletRequest request) throws UserNotFoundWithThisMobileNumber {
        Optional<UserEntity> user = userService.findByMobileNumber(dto.getMobileNumber());
        if(user.isEmpty()) {
            throw new UserNotFoundWithThisMobileNumber("No user with that phone number");
        }
        String email = user.get().getEmail();
        String token = getToken(user, request);
//        emailSenderService.sendEmail(
//                email,
//                token,
//                "password reset token"
//        );
        return new ResponseEntity<ForgetPasswordDto> (new ForgetPasswordDto(email,token,dto.getMobileNumber()), HttpStatus.OK);
    }

    private String getToken(Optional<UserEntity> user, HttpServletRequest request) {
        // generate a new token
        String randomGeneratedTokenPart = UUID.randomUUID().toString();
        ResetPasswordEntity newToken = new ResetPasswordEntity(randomGeneratedTokenPart, user.get());
        resetPasswordRepo.save(newToken);
        String token = "http://" +
                request.getServerName() + // localhost
                ":" +
                "3000" +
//                request.getServerPort() + // 8080
                request.getContextPath() +
                "/reset-password/" +
                randomGeneratedTokenPart;
//                "/reset-password?token=" + randomGeneratedTokenPart;
        return token;
    }

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token, @RequestBody ForgetPasswordDto dto)
//    public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestBody ForgetPasswordDto dto)
    {
        if(validateToken(token)){
            ResetPasswordEntity resetToken = resetPasswordRepo.findByToken(token);
            UserEntity user = resetToken.getUser();
            user.setPassword(dto.getNewPassword());
            userRepo.save(user);
//            return ResponseEntity.ok("successfully reset");
            return new ResponseEntity< ResponseDto > (new ResponseDto("successfully reset"),HttpStatus.OK);
        }
        else{
            return new ResponseEntity< ResponseDto > (new ResponseDto("failed to reset"),HttpStatus.INTERNAL_SERVER_ERROR);
//            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateToken(String token) {

        ResetPasswordEntity resetToken = resetPasswordRepo.findByToken(token);
        if(resetToken == null){
            return false;
        }

        UserEntity user = resetToken.getUser();

        if(resetToken.getExpiryDate().getTime() <= Calendar.getInstance().getTime().getTime()) {
            resetPasswordRepo.delete(resetToken); // we will delete the entry from the table
            // TODO: give an option to another token
            return false;
        }

        return true;
    }


}
