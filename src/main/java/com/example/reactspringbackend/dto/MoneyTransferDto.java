package com.example.reactspringbackend.dto;

import lombok.Data;

@Data
public class MoneyTransferDto {

    private String senderEmail;
    private String receiverEmail;
    private double amount;
}
