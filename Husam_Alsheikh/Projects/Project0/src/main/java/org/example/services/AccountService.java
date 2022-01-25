package org.example.services;

import org.example.dao.Repository;
import org.example.dto.AccountDTO;

import java.util.concurrent.atomic.AtomicInteger;

public class AccountService {
    Repository<Integer, AccountDTO> accountRepository;
    AtomicInteger accountNum = new AtomicInteger(1);

    public AccountService(Repository<Integer, AccountDTO> accountRepository) {
        this.accountRepository = accountRepository;
    }

    public int save(String name){
        AccountDTO accountDTO = new AccountDTO(name, accountNum.getAndIncrement());
        return accountRepository.save(accountDTO);
    }

    public AccountDTO getAccount(int id){
        return accountRepository.getById(id);
    }
}
