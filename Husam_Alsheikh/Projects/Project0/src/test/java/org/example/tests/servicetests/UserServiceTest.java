package org.example.tests.servicetests;

import junit.framework.TestCase;
import org.example.dao.PostgresUserDao;
import org.example.dao.UserRepository;
import org.example.dto.TransactionDTO;
import org.example.models.User;
import org.example.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

public class UserServiceTest extends TestCase {
    private UserRepository userRepoTest = Mockito.mock(PostgresUserDao.class);
    private UserService userServiceTest = new UserService(userRepoTest);

    @Test
    public void testGetUserByUsername() {
        User user = new User(1, "user", "pass");

        Mockito.when(userRepoTest.getByUsername("user")).thenReturn(user);

        Assert.assertEquals("Users are not the same", user, userServiceTest.getUserByUsername("user"));
    }

    @Test
    public void testGetUserByID() {
        User user = new User(1, "user", "pass");
        Mockito.when(userRepoTest.getById(1)).thenReturn(user);
        Assert.assertEquals("Users are not the same", user, userServiceTest.getUserByID(1));
    }

    @Test
    public void testUpdateUserTransaction() {
        TransactionDTO transactionDTO = new TransactionDTO(1, "user", 2, "transaction");
        User user = new User(1, "user", "pass", Arrays.asList(1, 2),
                Collections.singletonList(transactionDTO));
        userRepoTest.save(user);

        Mockito.when(userRepoTest.getById(1)).thenReturn(user);

        User temp = userRepoTest.getById(1);

        Assert.assertEquals("Transaction not updated", Collections.singletonList(transactionDTO), temp.getTransactions());
    }
}