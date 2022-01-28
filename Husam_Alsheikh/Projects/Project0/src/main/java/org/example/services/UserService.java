package org.example.services;

import org.example.dao.UserRepository;
import org.example.models.User;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public void addAccount(int accountNum, int userid){
        User user = userRepository.getById(userid);
        List<Integer> accounts = user.getAccountsId();

        accounts.add(accountNum);

        userRepository.save(user);
    }
}
