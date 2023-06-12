package com.example.reactspringbackend.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "User")

public class UserEntityDynamoDB {

    @DynamoDBHashKey
    private Long id;

    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private String gender;
}
