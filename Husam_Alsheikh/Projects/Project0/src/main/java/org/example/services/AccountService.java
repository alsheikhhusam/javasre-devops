package org.example.services;

import org.example.dao.InMemAccountDao;
import org.example.dao.Repository;
import org.example.dto.AccountDTO;

public class AccountService {
    Repository<Integer, AccountDTO> accountRepository;

    public AccountService(Repository<Integer, AccountDTO> accountRepository) {
        this.accountRepository = accountRepository;
    }

    public int save(int userId, String name){
        InMemAccountDao tempRepo = (InMemAccountDao) accountRepository;

        AccountDTO accountDTO = new AccountDTO(userId, tempRepo.getId(), name);
        return accountRepository.save(accountDTO);
    }

    public AccountDTO getAccount(int id){
        return accountRepository.getById(id);
    }
}
