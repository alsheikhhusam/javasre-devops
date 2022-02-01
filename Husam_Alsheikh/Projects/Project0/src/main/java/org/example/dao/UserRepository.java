package org.example.dao;

import org.example.models.User;

public interface UserRepository extends Repository<Integer, User> {
    /**
     * Method to get and return user using username
     * @author Husam Alsheikh
     * @param username username
     * @return returns user
     */
    User getByUsername(String username);
}
