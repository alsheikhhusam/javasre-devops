package org.example.dao;

import org.example.models.Roles;
import org.example.models.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemUserRepository implements UserRepository {
    private AtomicInteger idGen;
    private Map<Integer, User> users;

    public InMemUserRepository() {
        idGen = new AtomicInteger(1);
        users = new HashMap<>();

        users.put(idGen.get(), new User(idGen.getAndIncrement(), "husam.alsheikh", "7841", new HashSet<>(Collections.singletonList(Roles.ROLE_ADMIN))));
        users.put(idGen.get(), new User(idGen.getAndIncrement(), "alsheikh.husam", "7841", new HashSet<>(Collections.singletonList(Roles.ROLE_USER))));
        users.put(idGen.get(), new User(idGen.getAndIncrement(), "john.doe", "7841", new HashSet<>(Collections.singletonList(Roles.ROLE_USER))));
    }

    /**
     * Method to get user by username
     * @author Husam Alsheikh
     * @param username username
     * @return returns user
     */
    @Override
    public User getByUsername(String username) {
        return users.values()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    /**
     * Save user to repo
     * @author Husam Alsheikh
     * @param obj user
     * @return user id
     */
    @Override
    public Integer save(User obj) {
        return null;
    }

    /**
     * gets all users
     * @author Husam Alsheikh
     * @return returns list of users
     */
    @Override
    public List<User> getAll() {
        List<User> list = null;

        return list = users
                .values()
                .stream()
                .map(user -> new User(user.getId(), user.getUsername(), user.getPassword(), user.getAccountsId(), user.getRoles()))
                .collect(Collectors.toList());
    }

    /**
     * Gets user by id
     * @author Husam Alsheikh
     * @param integer user id
     * @return returns user
     */
    @Override
    public User getById(Integer integer) {
        return users.get(integer);
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param obj user
     */
    @Override
    public void delete(User obj) {

    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param integer user id
     */
    @Override
    public void deleteById(Integer integer) {

    }

    /**
     * Method to update user
     * @author Husam Alsheikh
     * @param obj updated user
     */
    @Override
    public void update(User obj) {  //  Update user
        users.replace(obj.getId(), obj);
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @return returns null
     */
    @Override
    public Integer getId() {
        return null;
    }
}
