package org.example.services;

import org.example.dao.UserRepository;
import org.example.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserService {
    private UserRepository userRepository;
    private Set<String> loggers;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        loggers = new HashSet<>();

        loggers.add("alsheikh.husam");
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

    public Set<String> getLoggers() {
        return loggers;
    }

    public void addLogger(String username){
        loggers.add(username);
    }
}
