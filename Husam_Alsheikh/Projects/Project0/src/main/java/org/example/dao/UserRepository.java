package org.example.dao;

import org.example.dao.Repository;
import org.example.models.User;

public interface UserRepository extends Repository<Integer, User> {
    User getByUsername(String username);
}
