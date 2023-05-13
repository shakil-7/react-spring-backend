package com.example.reactspringbackend.controller;


import com.example.reactspringbackend.dto.AddMoneyDto;
import com.example.reactspringbackend.dto.MoneyTransferDto;
import com.example.reactspringbackend.dto.ResponseDto;
import com.example.reactspringbackend.dto.TransactionDetailsDto;
import com.example.reactspringbackend.entity.TransactionEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InsufficientBalanceException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisMobileNumber;
import com.example.reactspringbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/add_money")
    public ResponseEntity<?> addMoney(@RequestBody AddMoneyDto dto) throws UserNotFoundWithThisMobileNumber {
//        System.out.println(dto);
        userService.addMoney(dto);
        return new ResponseEntity< ResponseDto > (new ResponseDto("success"),HttpStatus.OK);
    }

    @PostMapping("/send_money")
    public ResponseEntity<?> sendMoney(@RequestBody MoneyTransferDto dto) throws UserNotFoundWithThisMobileNumber, InsufficientBalanceException {
//        System.out.println("dto = " + dto);
        userService.sendMoney(dto);
        return new ResponseEntity< ResponseDto> (new ResponseDto("success"),HttpStatus.OK);
    }

    @GetMapping("/user/transaction")
    public ResponseEntity<List<TransactionDetailsDto>> getTransaction(@RequestParam(required = false) String mobileNumber) {
//        System.out.println("mobileNumber = " + mobileNumber);
        List<TransactionDetailsDto> allTransaction = userService.getTransaction(mobileNumber);
        System.out.println("allTransaction = " + allTransaction);
        return new ResponseEntity<List<TransactionDetailsDto>> (allTransaction, HttpStatus.OK);
    }
}
