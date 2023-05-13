package com.example.reactspringbackend.repository;

import com.example.reactspringbackend.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {


//    @Query(
//            value = "select * from transaction_entity WHERE sender = 1 or recipient = 1",
//            nativeQuery = true
//    )
    @Query(
            value = "SELECT id, date, recipient as otherParty, -1*amount as amount FROM transaction_entity WHERE sender = :number UNION ALL SELECT id, date, sender as otherParty, amount as amount FROM transaction_entity WHERE recipient = :number",
            nativeQuery = true
    )
    List<Object[]> getAllTransactionFromSingleUser(@Param("number") String mobileNumber);


//    List<TestEntity> findAllByAddressCountryIgnoreCase(String country);
//
////    @Query(value = "SELECT x FROM TestEntity x where x.userEmail like %?1%")
////    @Query(value = "select * from user_table where user_email like %?1%", nativeQuery = true)
//    @Query(value = "select * from user_table where user_email like %:emailSubdomain%", nativeQuery = true)
//    List<TestEntity> getUsersByEmailDomain(@Param("emailSubdomain") String emailSubdomain);
//
//
//    @Modifying
//    @Transactional
//    @Query(
//            value = "UPDATE user_table SET user_name = ?2 WHERE user_email = ?1",
//            nativeQuery = true
//    )
//    void changeUserNameByEmail(String email, String newName);
}


