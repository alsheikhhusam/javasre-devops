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

        accounts.put(idGen.get(), new AccountDTO(idGen.get(), "husam.alsheikh", 1000, idGen.getAndIncrement()));
        accounts.put(idGen.get(), new AccountDTO(idGen.get(), "alsheikh.husam", 200, idGen.getAndIncrement()));
    }

    @Override
    public Integer save(AccountDTO obj) {   //  Save/Create new account to repository
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
        accounts.replace(obj.getAccountNum(), obj);
    }

    @Override
    public Integer getId() {
        return idGen.get();
    }
}
