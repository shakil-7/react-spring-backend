package com.example.reactspringbackend.repository;

import com.example.reactspringbackend.entity.AccountEntity;
import com.example.reactspringbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity,Long> {


    AccountEntity findByUser(UserEntity userEntity);

    void deleteUserByUser(UserEntity userEntity);
}
