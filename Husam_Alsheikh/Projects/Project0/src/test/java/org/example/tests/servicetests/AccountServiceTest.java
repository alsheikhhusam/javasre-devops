package org.example.tests.servicetests;

import org.example.dao.PostgresAccountDao;
import org.example.dao.Repository;
import org.example.dto.AccountDTO;
import org.example.services.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AccountServiceTest {
    private Repository<Integer, AccountDTO> accountRepoTest = Mockito.mock(PostgresAccountDao.class);
    private AccountService accountServiceTest = new AccountService(accountRepoTest);

    @Test
    public void saveTest(){
        int expected = 1;

        Mockito.when(accountRepoTest.save(new AccountDTO())).thenReturn(1);
        int num = accountServiceTest.save(2, "alsheikh.husam");
        Assert.assertEquals("Numbers do not match", expected, num);
    }

    @Test
    public void transferTest(){
        AccountDTO expected = new AccountDTO(2, "alsheikh", 300, 1);

        Mockito.when(accountRepoTest.getById(1)).thenReturn(expected);
        AccountDTO actual = accountServiceTest.getAccount(1);

        Assert.assertEquals("Transfer did not transfer the correct account", expected.getBalance(), actual.getBalance());
    }

    @Test
    public void updateBalanceTest(){
        AccountDTO temp = new AccountDTO(2, "alsheikh", 200, 2);
        int expected = 200;

        Mockito.when(accountRepoTest.getById(2)).thenReturn(temp);
        accountRepoTest.update(temp);
        AccountDTO actual = accountServiceTest.getAccount(2);

        Assert.assertEquals("Update Balance did not update the correct balance", expected, actual.getBalance());
    }

    @Test
    public void getAccountTest(){
        AccountDTO expected = new AccountDTO(2, 200, "alshe");

        Mockito.when(accountRepoTest.getById(2)).thenReturn(expected);

        Assert.assertEquals("Accounts do not match", expected, accountServiceTest.getAccount(2));
    }
}
