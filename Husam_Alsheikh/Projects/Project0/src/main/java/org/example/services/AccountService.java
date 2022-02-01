package org.example.services;

import org.example.dao.Repository;
import org.example.dto.AccountDTO;

public class AccountService {
    Repository<Integer, AccountDTO> accountRepository;


    public AccountService(Repository<Integer, AccountDTO> accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Method to save account to database
     * @author Husam Alsheikh
     * @param userId userid of the user that the account belongs to
     * @param name username of the user that the account belongs to
     * @return returns account number of the saved account
     */
    public int save(int userId, String name){
        AccountDTO accountDTO = new AccountDTO(userId, 0, name);
        return accountRepository.save(accountDTO);
    }

    /**
     * Method to transfer balance from one account to another
     * @author Husam Alsheikh
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
     * Method to update balance for an account
     * @author Husam Alsheikh
     * @param newBalance account with the updated new balance
     */
    public void updateBalance(AccountDTO newBalance){
        accountRepository.update(newBalance);
    }

    /**
     * Method to return account using account number
     * @author  Husam Alsheikh
     * @param id account number
     * @return returns the account with the account number of the parameter
     */
    public AccountDTO getAccount(int id){
        return accountRepository.getById(id);
    }
}
