package com.example.reactspringbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sender;
    private String recipient;
    private Date date;
    private String type;
    private boolean status;
    private double amount;

    public TransactionEntity(String senderPhoneNumber, String receiverPhoneNumber, double amount, Date date, boolean b, String debited) {
        this.sender = senderPhoneNumber;
        this.recipient = receiverPhoneNumber;
        this.date = date;
        this.amount = amount;
        this.status = b;
        this.type = debited;
    }

    //    @ManyToOne(
//            fetch = FetchType.EAGER
//    )
//    @JoinColumn(
//            name = "user_id",
//            referencedColumnName = "id",
//            nullable = false
//    )
//    private UserEntity user;
}
