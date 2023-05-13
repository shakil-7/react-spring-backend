package com.example.reactspringbackend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDetailsDto {

    private Long id;
    private String date;
    private String otherParty;
    private double amount;

    public TransactionDetailsDto(Long id, String date, String otherParty, double amount) {
        this.id = id;
        this.date = date;
        this.otherParty = otherParty;
        this.amount = amount;
    }
}
