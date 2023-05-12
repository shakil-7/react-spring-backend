package com.example.reactspringbackend.dto;

import lombok.Data;

@Data
public class MoneyTransferDto {

    private String senderPhoneNumber;
    private String receiverPhoneNumber;
    private double amount;
}
