package org.example.services;

import org.example.dao.Repository;
import org.example.dto.AccountDTO;

public class AccountService {
    Repository<Integer, AccountDTO> accountRepository;


    /**
     * @Author Husam Alsheikh
     * @param accountRepository account repository (PostgresAccountDao)
     */
    public AccountService(Repository<Integer, AccountDTO> accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * @Author Husam Alsheikh
     * @param userId userid of the user that the account belongs to
     * @param name username of the user that the account belongs to
     * @return returns account number of the saved account
     */
    public int save(int userId, String name){
        AccountDTO accountDTO = new AccountDTO(userId, 0, name);
        return accountRepository.save(accountDTO);
    }

    /**
     * @Author Husam Alsheikh
     * @param from account that is sending the balance
     * @param to account that is receiving the balance
     * @param amount amount which is being transferred
     * @return returns the updated account which received the transfer
     */
    public AccountDTO transfer(AccountDTO from, AccountDTO to, int amount){
        //  Subtract and update from sending account
        from.setBalance(from.getBalance() - amount);
        updateBalance(from);

        //  Add and update from receiving account
        to.setBalance(to.getBalance() + amount);
        updateBalance(to);

        return accountRepository.getById(to.getAccountNum());
    }

    /**
     * @Author Husam Alsheikh
     * @param newBalance Account with the updated new balance
     */
    public void updateBalance(AccountDTO newBalance){
        accountRepository.update(newBalance);
    }

    /**
     * @Author  Husam Alsheikh
     * @param id account number
     * @return returns the account with the account number of the parameter
     */
    public AccountDTO getAccount(int id){
        return accountRepository.getById(id);
    }

    public void updateAccount(AccountDTO accountDTO){
        accountRepository.update(accountDTO);
    }
}
