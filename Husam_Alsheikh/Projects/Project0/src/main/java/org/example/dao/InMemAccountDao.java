package org.example.dao;

import org.example.dto.AccountDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemAccountDao implements Repository<Integer, AccountDTO>{   //  <id, accountNum>
    AtomicInteger idGen;
    Map<Integer, AccountDTO> accounts;

    public InMemAccountDao(){
        idGen = new AtomicInteger(1);
        accounts = new HashMap<>();

        accounts.put(idGen.get(), new AccountDTO(2, idGen.getAndIncrement(), 200, "alsheikh.husam"));
    }

    public int getId(){
        return idGen.get();
    }

    @Override
    public Integer save(AccountDTO obj) {   //  Save new account to repository
        accounts.put(idGen.get(), obj);
        return idGen.getAndIncrement();
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

    @Override
    public void update(AccountDTO obj) {
        accounts.put(obj.getAccountNum(), obj);
    }
}
