package org.example.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemGreetingDao implements Repository<Integer, String>{
    AtomicInteger idGen = new AtomicInteger(1);
    Map<Integer, String> greetings = new HashMap<>();

    @Override
    public Integer save(String obj) {
        greetings.put(idGen.getAndIncrement(), obj);
        return idGen.get();
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
