package org.example.dao;

import java.util.List;

public class PostgresGreetingDao implements Repository<Integer, String>{
    @Override
    public Integer save(String obj) {
        return null;
    }

    @Override
    public List<String> getAll() {
        return null;
    }

    @Override
    public String getById(Integer integer) {
        return null;
    }

    @Override
    public void delete(String obj) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
