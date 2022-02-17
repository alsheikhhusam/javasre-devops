package com.example.ers.services;

import com.example.ers.entities.ReimbsUser;
import com.example.ers.entities.repos.ReimbsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReimbsUserService {
    // get user by email
    // get user by id

    private ReimbsUserRepository userRepository;

    @Autowired
    public ReimbsUserService(ReimbsUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<ReimbsUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<ReimbsUser> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
