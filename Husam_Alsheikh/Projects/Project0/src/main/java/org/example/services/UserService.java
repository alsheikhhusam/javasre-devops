package org.example.services;

import org.example.dao.UserRepository;
import org.example.dto.TransactionDTO;
import org.example.models.User;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.getByUsername(username);
        return user;
    }

    public User getUserByID(int userid){ return userRepository.getById(userid); }

    public void addAccount(int accountNum, int userid){ //  Add account to user and update user
        User user = userRepository.getById(userid); //  Get user by id

        //  Get accounts from user
        List<Integer> accounts = user.getAccountsId();

        //  Add account and update user
        accounts.add(accountNum);
        userRepository.update(user);
    }

    public void updateUserTransaction(TransactionDTO transactionDTO){
        //  Get User and add the transaction
        User updatedUser = userRepository.getById(transactionDTO.getUserid());
        updatedUser.addTransaction(transactionDTO);

        //  Add Transaction
        userRepository.save(updatedUser);
    }
}
