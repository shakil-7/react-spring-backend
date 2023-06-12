package com.example.reactspringbackend.entity;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntityDynamoDBTest {

    void addNewUser(){
        int n = 1;
        for(int i=0;i<n;i++){
            UserEntityDynamoDB user = new UserEntityDynamoDB();
            user.setId((long)i+1);
            user.setName("user_" + i);
            user.setEmail("user_" + i+1 + "@gmail.com");
            user.setPassword("password_" + i);
            user.setMobileNumber("mobile_" + i);
        }
    }
}