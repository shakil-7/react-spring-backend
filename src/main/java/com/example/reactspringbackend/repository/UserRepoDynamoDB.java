package com.example.reactspringbackend.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.reactspringbackend.dto.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoDynamoDB {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void save(SignUpRequestDto dto) {
        dynamoDBMapper.save(dto);
    }
}
