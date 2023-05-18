package com.example.reactspringbackend.repository;

import com.example.reactspringbackend.entity.ResetPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordRepo extends JpaRepository<ResetPasswordEntity, Long> {
    ResetPasswordEntity findByToken(String token);
}
