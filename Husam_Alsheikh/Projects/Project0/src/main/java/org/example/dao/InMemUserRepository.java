package org.example.dao;

import org.example.models.User;

import java.util.List;

public class InMemUserRepository implements UserRepository{
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
        return null;
    }
}
