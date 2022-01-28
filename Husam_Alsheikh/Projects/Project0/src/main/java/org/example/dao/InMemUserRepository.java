package org.example.dao;

import org.example.models.Roles;
import org.example.models.User;

import javax.management.relation.Role;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemUserRepository implements UserRepository {
    private AtomicInteger idGen;
    private Map<Integer, User> users;

    public InMemUserRepository() {
        idGen = new AtomicInteger(1);
        users = new HashMap<>();

        users.put(idGen.get(), new User(idGen.getAndIncrement(), "husam.alsheikh", "7841", new HashSet<>(Collections.singletonList(Roles.EMPLOYEE))));
        users.put(idGen.get(), new User(idGen.getAndIncrement(), "alsheikh.husam", "7841", new HashSet<>(Collections.singletonList(Roles.USER))));
        users.put(idGen.get(), new User(idGen.getAndIncrement(), "john.doe", "7841", new HashSet<>(Collections.singletonList(Roles.USER))));
    }

    @Override
    public User getByUsername(String username) {
        return users.values()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    @Override
    public Integer save(User obj) {
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> list = null;

        return list = users
                .values()
                .stream()
                .map(user -> new User(user.getId(), user.getUsername(), user.getPassword(), user.getAccountsId(), user.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public User getById(Integer integer) {
        return users.get(integer);
    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void update(User obj) {  //  Update user
        users.replace(obj.getId(), obj);
    }

    @Override
    public Integer getId() {
        return null;
    }
}
