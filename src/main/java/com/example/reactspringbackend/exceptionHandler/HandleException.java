package com.example.reactspringbackend.exceptionHandler;

import com.example.reactspringbackend.exceptionHandler.allTypeOfException.NotUniqueEmailException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseStatus
public class HandleException {

    @ExceptionHandler(RegisterNewUserException.class)
    public ResponseEntity<ExceptionResponseDto> newRegisterException(RegisterNewUserException exception, WebRequest request) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.ok(responseDto);
    }

    @ExceptionHandler(NotUniqueEmailException.class)
    public ResponseEntity<ExceptionResponseDto> notUniqueEmail(NotUniqueEmailException exception, WebRequest request) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), false);
        return ResponseEntity.ok(responseDto);
    }
}
