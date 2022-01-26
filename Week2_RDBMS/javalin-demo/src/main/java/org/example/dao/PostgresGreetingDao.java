package org.example.dao;

import org.example.models.Greeting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PostgresGreetingDao implements Repository<Integer, Greeting>{
    @Override
    public Integer save(String obj) {
        return 0;
    }

    @Override
    public List<Greeting> getAll() {
        return null;
    }

    @Override
    public Greeting getById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Greeting obj) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
