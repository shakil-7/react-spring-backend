package com.example.reactspringbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordEntity {

    private static final int EXPIRATION = 3;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    public ResetPasswordEntity(String token, UserEntity user) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDateCalculation();
    }

    @OneToOne(
            targetEntity = UserEntity.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private UserEntity user;

    private Date expiryDate;

    private Date expiryDateCalculation() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, ResetPasswordEntity.EXPIRATION);
        return new Date(calendar.getTime().getTime());
    }
}
