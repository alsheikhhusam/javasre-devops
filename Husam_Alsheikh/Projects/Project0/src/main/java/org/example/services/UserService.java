package org.example.services;

import org.example.dao.UserRepository;
import org.example.dto.TransactionDTO;
import org.example.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to get user by username
     * @author Husam Alsheikh
     * @param username username of user
     * @return returns User
     */
    public User getUserByUsername(String username) {
        User user = userRepository.getByUsername(username);
        return user;
    }

    /**
     * Method to get user by ID
     * @author Husam Alsheikh
     * @param userid id of user
     * @return returns user
     */
    public User getUserByID(int userid){ return userRepository.getById(userid); }


    /**
     * Method to add transaction to database
     * @author Husam Alsheikh
     * @param transactionDTO transaction object containing transaction details
     */
    public void updateUserTransaction(TransactionDTO transactionDTO){
        //  Get User and add the transaction
        User updatedUser = userRepository.getById(transactionDTO.getUserid());
        updatedUser.addTransaction(transactionDTO);

        //  Add Transaction
        userRepository.save(updatedUser);

        logger.info("New transaction added to user");
    }
}
