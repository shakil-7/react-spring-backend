package com.example.reactspringbackend.exceptionHandler;

import com.example.reactspringbackend.exceptionHandler.allTypeOfException.*;
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
    public ResponseEntity<?> newRegisterException(RegisterNewUserException exception, WebRequest request) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( 500, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(NotUniqueEmailException.class)
    public ResponseEntity<?> notUniqueEmail(NotUniqueEmailException exception, WebRequest request) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( 500, exception.getMessage(), false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(UserNotFoundWithThisEmail.class)
    public ResponseEntity<?> userNotFoundWithThisEmail(UserNotFoundWithThisEmail exception, WebRequest request) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( 404, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<?> internalServerError(InternalServerError exception, WebRequest request) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( 500, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> internalServerError(InvalidPasswordException exception, WebRequest request) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( 400, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> internalServerError(InsufficientBalanceException exception, WebRequest request) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( 400, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}
