package com.example.reactspringbackend.service;

import com.example.reactspringbackend.dto.*;
import com.example.reactspringbackend.entity.AccountEntity;
import com.example.reactspringbackend.entity.TransactionEntity;
import com.example.reactspringbackend.entity.UserEntity;
import com.example.reactspringbackend.exceptionHandler.allTypeOfException.*;
import com.example.reactspringbackend.repository.AccountRepo;
import com.example.reactspringbackend.repository.TransactionRepo;
import com.example.reactspringbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public void registerNewUser(SignUpRequestDto requestDto) throws RegisterNewUserException, NotUniqueMobileNumberException {
        UserEntity user = new UserEntity();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setMobileNumber(requestDto.getMobileNumber());
        user.setGender(requestDto.getGender());
        try {
            userRepo.save(user);
            accountRepo.save(new AccountEntity(user));

        } catch (Exception e) {
            if(!isUniqueMobileNumber(user.getMobileNumber())){
                throw new NotUniqueMobileNumberException("Already a account associated with this number");
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
    public void deleteUserByEmail(String email) throws UserNotFoundWithThisMobileNumber {
        Optional<UserEntity> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            accountRepo.deleteUserByUser(user.get());
            userRepo.deleteByEmail(user.get().getEmail());
        }
        else{
            throw new UserNotFoundWithThisMobileNumber("There is no user with this email");
        }
    }

    @Override
    public boolean login(LoginDto loginDto) throws UserNotFoundWithThisMobileNumber {

        String mobileNumber = loginDto.getMobileNumber();
        String password = loginDto.getPassword();

        Optional<UserEntity> user = userRepo.findByMobileNumber(mobileNumber);
        if(user.isPresent()){
            return user.get().getPassword().equals(password);
        }
        else{
            throw new UserNotFoundWithThisMobileNumber("There is no user with this number");
        }
    }

    @Override
    public UserDetailsDto getUserDetails(String mobileNumber) throws InternalServerError, UserNotFoundWithThisMobileNumber {
        Optional<UserEntity> user;
        try{
            user =  userRepo.findByMobileNumber(mobileNumber);
        }catch(Exception e){
            throw new InternalServerError("Something went wrong");
        }


        if(user.isPresent()){
//            System.out.println("user.get() = " + user.get());
            AccountEntity account = accountRepo.findByUser(user.get());
            UserDetailsDto details = new UserDetailsDto(user.get(), account);
            return details;
        }
        else{
            throw new UserNotFoundWithThisMobileNumber("There is no user with this number");
        }
    }

    @Override
    public void addMoney(AddMoneyDto dto) throws UserNotFoundWithThisMobileNumber {

        Optional<UserEntity> user = userRepo.findByMobileNumber(dto.getMobileNumber());
        if(user.isPresent()){
            AccountEntity account = accountRepo.findByUser(user.get());
            account.setBalance(dto.getAmount() + account.getBalance());
            accountRepo.save(account);
            return;
        }
        throw new UserNotFoundWithThisMobileNumber("No user with mobile " + dto.getMobileNumber());
    }

    @Override
    public void sendMoney(MoneyTransferDto dto) throws UserNotFoundWithThisMobileNumber, InsufficientBalanceException {
        String senderPhoneNumber = dto.getSenderPhoneNumber();
        String receiverPhoneNumber = dto.getReceiverPhoneNumber();
        double amount = dto.getAmount();
        
        

        Optional<UserEntity> sender = userRepo.findByMobileNumber(senderPhoneNumber);
        if(sender.isPresent()){
//            System.out.println("sender.get() = " + sender.get());
            Optional<UserEntity> receiver = userRepo.findByMobileNumber(receiverPhoneNumber);
            if(receiver.isPresent()){

                AccountEntity senderAccount = accountRepo.findByUser(sender.get());
                AccountEntity receiverAccount = accountRepo.findByUser(receiver.get());

                double senderCurrentBalance = senderAccount.getBalance();
                double receiverCurrentBalance = receiverAccount.getBalance();

                if(senderCurrentBalance < amount){
                    throw new InsufficientBalanceException("Not Enough Balance");
                }
                else{
                    senderAccount.setBalance(senderCurrentBalance - amount);
                    receiverAccount.setBalance(receiverCurrentBalance + amount);
                    accountRepo.save(senderAccount);
                    accountRepo.save(receiverAccount);


                    /// success

                    TransactionEntity transaction = new TransactionEntity(
                            senderPhoneNumber, receiverPhoneNumber, amount,
                            new Date(), true, "debited"
                    );

                    transactionRepo.save(transaction);

                }

            }
            else{
                throw new UserNotFoundWithThisMobileNumber("No user with email: \n" + dto.getReceiverPhoneNumber());
            }
        }
        else{
            throw new UserNotFoundWithThisMobileNumber("No user with email: \n" + dto.getSenderPhoneNumber());
        }
    }

    @Override
    public void deleteUserByMobileNumber(String mobileNumber) throws UserNotFoundWithThisMobileNumber {
        Optional<UserEntity> user = userRepo.findByMobileNumber(mobileNumber);
        if(user.isPresent()){
            accountRepo.deleteUserByUser(user.get());
            userRepo.deleteByMobileNumber(user.get().getMobileNumber());
        }
        else{
            throw new UserNotFoundWithThisMobileNumber("There is no user with this email");
        }
    }

    @Override
    public List<TransactionEntity> getTransaction(String mobileNumber) {
        List<TransactionEntity> queryResult = transactionRepo.findAll();
        return queryResult;
//        List<TransactionDetailsDto> dto = new ArrayList<TransactionDetailsDto>();
    }

    private boolean isUniqueMobileNumber(String mobileNumber) {
        Optional<UserEntity> user = userRepo.findByMobileNumber(mobileNumber);
        return user.isEmpty();
    }
}