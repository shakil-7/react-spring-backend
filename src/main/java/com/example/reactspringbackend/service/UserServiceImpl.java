package com.example.reactspringbackend.service;

import com.example.reactspringbackend.dto.SignUpRequestDto;
import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.NotUniqueEmailException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;
import com.example.reactspringbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public void registerNewUser(SignUpRequestDto requestDto) throws RegisterNewUserException, NotUniqueEmailException {
        UserEntity user = new UserEntity();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setGender(requestDto.getGender());
        try {
            userRepo.save(user);
        } catch (Exception e) {

            if(!isUniqueEmail(user.getEmail())){
                throw new NotUniqueEmailException("Already a account associated with this email");
            }
            else {
                throw new RegisterNewUserException("Something went wrong. Please try again");
            }


        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    private boolean isUniqueEmail(String email) {
        Optional<UserEntity> user = userRepo.findByEmail(email);
        return user.isEmpty();
    }
}