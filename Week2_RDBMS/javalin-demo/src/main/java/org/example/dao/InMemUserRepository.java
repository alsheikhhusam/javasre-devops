package org.example.dao;

import org.example.models.Roles;
import org.example.models.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemUserRepository implements UserRepository {
    private AtomicInteger idGen;
    private Map<Integer, User> users;

    public InMemUserRepository() {
        idGen = new AtomicInteger(1);
        users = new HashMap<>();
        users.put(idGen.getAndIncrement(), new User(idGen.get(), "august.duet", "p@$$w0rd123", new HashSet<Roles>(Arrays.asList(Roles.ROLE_ADMIN, Roles.ROLE_USER))));
        users.put(idGen.getAndIncrement(), new User(idGen.get(), "john.doe", "p@$$w0rd123", new HashSet<>(Arrays.asList(Roles.ROLE_USER))));
    }

    @Override
    public Integer save(User obj) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Integer integer) {
        return null;
    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public User getByUsername(String username) {
        return users.values()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }
}
