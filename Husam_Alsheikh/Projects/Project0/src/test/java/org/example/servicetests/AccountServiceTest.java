package org.example.servicetests;

import org.example.dao.InMemAccountDao;
import org.example.dao.Repository;
import org.example.dto.AccountDTO;
import org.example.services.AccountService;
import org.junit.Assert;
import org.junit.Test;

public class AccountServiceTest {
    private Repository<Integer, AccountDTO> accountRepoTest = new InMemAccountDao();
    private AccountService accountServiceTest = new AccountService(accountRepoTest);

    @Test
    public void save(){
        int userid = 2;
        String name = "alsheikh.husam";

        int expected = 2;
        int actual = accountServiceTest.save(userid, name);

        Assert.assertEquals("Save did not return the correct number", expected, actual);
    }

    @Test
    public void updateBalance(){
        AccountDTO expected = new AccountDTO(2, "alsheikh.husam", 250, 1);

        accountServiceTest.updateBalance(expected);

        AccountDTO actual = accountServiceTest.getAccount(1);

        Assert.assertEquals("Update did not update the correct account", expected.getBalance(), actual.getBalance());
    }

    @Test
    public void addAccount(){
        int num = accountServiceTest.save(2, "alsheikh.husam");

        AccountDTO expected = new AccountDTO(2, "alsheikh.husam", 200, 1);
        AccountDTO actual = accountServiceTest.getAccount(1);

        Assert.assertEquals("GetAccount did not return the correct accountNum", expected.getAccountNum(), actual.getAccountNum());
        Assert.assertEquals("GetAccount did not return the correct username", expected.getUsername(), actual.getUsername());
        Assert.assertEquals("GetAccount did not return the correct balance", expected.getBalance(), actual.getBalance());
        Assert.assertEquals("GetAccount did not return the correct userid", expected.getUserid(), actual.getUserid());
    }
}
