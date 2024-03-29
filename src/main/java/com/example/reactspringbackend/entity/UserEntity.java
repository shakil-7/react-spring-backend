package com.example.reactspringbackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(
       uniqueConstraints = @UniqueConstraint(
               columnNames = {"email"},
               name = "email_unique"
       )
)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String gender;
}
