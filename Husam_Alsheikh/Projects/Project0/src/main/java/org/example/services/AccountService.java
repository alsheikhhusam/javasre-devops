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

    public AccountDTO transfer(AccountDTO from, AccountDTO to, int amount){
        //  Subtract and update from sending account
        from.setBalance(from.getBalance() - amount);
        updateBalance(from);

        //  Add and update from receiving account
        to.setBalance(to.getBalance() + amount);
        updateBalance(to);

        return accountRepository.getById(to.getAccountNum());
    }

    public int getBalance(int accountNum){
        AccountDTO temp = accountRepository.getById(accountNum);

        return temp.getBalance();
    }

    public void updateBalance(AccountDTO newBalance){
        accountRepository.update(newBalance);
    }

    public AccountDTO getAccount(int id){
        return accountRepository.getById(id);
    }
}
