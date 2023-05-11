package com.example.reactspringbackend.service;

import com.example.reactspringbackend.dto.AddMoneyDto;
import com.example.reactspringbackend.dto.LoginDto;
import com.example.reactspringbackend.dto.SignUpRequestDto;
import com.example.reactspringbackend.dto.UserDetailsDto;
import com.example.reactspringbackend.entity.AccountEntity;
import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.InternalServerError;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.NotUniqueEmailException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.RegisterNewUserException;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.UserNotFoundWithThisEmail;
import com.example.reactspringbackend.repository.AccountRepo;
import com.example.reactspringbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public void registerNewUser(SignUpRequestDto requestDto) throws RegisterNewUserException, NotUniqueEmailException {
        UserEntity user = new UserEntity();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setGender(requestDto.getGender());
        try {
            userRepo.save(user);
            accountRepo.save(new AccountEntity(user));

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


    @Override
    public void deleteUserByEmail(String email) throws UserNotFoundWithThisEmail {
        Optional<UserEntity> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            accountRepo.deleteUserByUser(user.get());
            userRepo.deleteByEmail(user.get().getEmail());
        }
        else{
            throw new UserNotFoundWithThisEmail("There is no user with this email");
        }
    }

    @Override
    public boolean login(LoginDto loginDto) throws UserNotFoundWithThisEmail {

        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        Optional<UserEntity> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            return user.get().getPassword().equals(password);
        }
        else{
            throw new UserNotFoundWithThisEmail("There is no user with this email");
        }
    }

    @Override
    public UserDetailsDto getUserDetails(String email) throws InternalServerError, UserNotFoundWithThisEmail {


        Optional<UserEntity> user;
        try{
            user =  userRepo.findByEmail(email);
        }catch(Exception e){
            throw new InternalServerError("Something went wrong");
        }


        if(user.isPresent()){
            System.out.println("user.get() = " + user.get());
            AccountEntity account = accountRepo.findByUser(user.get());
            UserDetailsDto details = new UserDetailsDto(user.get(), account);
            return details;
        }
        else{
            throw new UserNotFoundWithThisEmail("There is no user with this email");
        }
    }

    @Override
    public void addMoney(AddMoneyDto dto) throws UserNotFoundWithThisEmail {

        Optional<UserEntity> user = userRepo.findByEmail(dto.getEmail());
        if(user.isPresent()){
            AccountEntity account = accountRepo.findByUser(user.get());

            account.setBalance(dto.getAmount() + account.getBalance());
            accountRepo.save(account);
            return;
        }
        throw new UserNotFoundWithThisEmail("No user with email " + dto.getEmail());
    }

    private boolean isUniqueEmail(String email) {
        Optional<UserEntity> user = userRepo.findByEmail(email);
        return user.isEmpty();
    }
}