package com.example.reactspringbackend.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.reactspringbackend.dto.SignUpRequestDto;
import com.example.reactspringbackend.entity.UserEntityDynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoDynamoDB {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void save(SignUpRequestDto dto) {

        UserEntityDynamoDB user = new UserEntityDynamoDB();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setMobileNumber(dto.getMobileNumber());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());

        dynamoDBMapper.save(user);
    }
}
