package com.example.reactspringbackend.dto;

import com.example.reactspringbackend.entity.AccountEntity;
import com.example.reactspringbackend.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailsDto {

    private Long id;

    private String name;
    private String email;
    private String password;
    private String gender;

    private long accountNumber;
    private double balance;


    public UserDetailsDto(UserEntity user, AccountEntity account) {

        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();

        this.accountNumber = account.getId();
        this.balance = account.getBalance();
    }

}
