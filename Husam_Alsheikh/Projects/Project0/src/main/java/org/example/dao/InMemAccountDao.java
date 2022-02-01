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

    /**
     * Method to save accounts
     * @author Husam Alsheikh
     * @param obj account to be saved
     * @return returns account number
     */
    @Override
    public Integer save(AccountDTO obj) {   //  Save/Create new account to repository
        accounts.put(idGen.get(), obj);
        return idGen.getAndIncrement();
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @return returns null
     */
    @Override
    public List<AccountDTO> getAll() {
        return null;
    }

    /**
     * Method to get account by id
     * @author Husam Alsheikh
     * @param integer account number
     * @return returns account
     */
    @Override
    public AccountDTO getById(Integer integer) {
        return accounts.get(integer);
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param obj account
     */
    @Override
    public void delete(AccountDTO obj) {

    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param integer account number
     */
    @Override
    public void deleteById(Integer integer) {

    }

    /**
     * Method to update account
     * @author Husam Alsheikh
     * @param obj account with updated information
     */
    @Override
    public void update(AccountDTO obj) {
        accounts.replace(obj.getAccountNum(), obj);
    }

    /**
     * Method to return current id
     * @author Husam Alsheikh
     * @return returns id
     */
    @Override
    public Integer getId() {
        return idGen.get();
    }
}
