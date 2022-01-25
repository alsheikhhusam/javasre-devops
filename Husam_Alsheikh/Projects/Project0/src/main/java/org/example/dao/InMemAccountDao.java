package org.example.dao;

import org.example.dto.AccountDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemAccountDao implements Repository<Integer, AccountDTO>{   //  <id, accountNum>
    AtomicInteger idGen = new AtomicInteger(1); //  Thread safe integer
    Map<Integer, AccountDTO> accounts = new HashMap<>();

    @Override
    public Integer save(AccountDTO obj) {
        accounts.put(idGen.getAndIncrement(), obj);
        return idGen.get();
    }

    @Override
    public List<AccountDTO> getAll() {
        return null;
    }

    @Override
    public AccountDTO getById(Integer integer) {
        return accounts.get(integer);
    }

    @Override
    public void delete(AccountDTO obj) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
