package com.example.reactspringbackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double balance = 0.0;


    public AccountEntity(UserEntity user) {
        this.user = user;
    }

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name="customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_id")
    )
    private UserEntity user;
}
