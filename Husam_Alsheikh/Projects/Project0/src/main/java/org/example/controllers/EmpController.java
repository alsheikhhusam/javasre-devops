package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import org.example.dto.CreateAccountDTO;
import org.example.services.AccountService;
import org.example.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmpController implements CrudHandler {
    private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
    private AccountService accountService;
    private UserService userService;

    public EmpController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    /**
     * Handles account creation. Uploads account to repository
     * @author Husam Alsheikh
     * @param context Context
     */
    @Override
    public void create(@NotNull Context context) {  //  Create Account
        CreateAccountDTO newAccount = context.bodyAsClass(CreateAccountDTO.class);

        // Only allow to open an account for a user if they enter correct user id and username
        //  Allow to create a bank account only if username is a known user
        if(userService.getUserByUsername(newAccount.getUsername()) == null){
            throw new ForbiddenResponse("Invalid Username - User does not exist");
        }
        //  Allow to create a bank account only if userid is a known user
        if(userService.getUserByUsername(newAccount.getUsername()).getId() != newAccount.getUserid()){
            throw new ForbiddenResponse("Invalid User ID - User does not exist");
        }

        //  Save account to accountRepo and update account of user
        int accountNum = accountService.save(newAccount.getUserid(), newAccount.getUsername());
        userService.addAccount(accountNum, newAccount.getUserid());

        logger.info("Account saved to database");

        context.header("Location", "http://localhost:4200/createAccount/" + accountNum);
        context.status(201);
        context.json(accountService.getAccount(accountNum));
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param context Context
     * @param s url parameter
     */
    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        throw new ForbiddenResponse("Function has no implementation");
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param context Context
     */
    @Override
    public void getAll(@NotNull Context context) {
        throw new ForbiddenResponse("Function has no implementation");
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param context Context
     * @param s url parameter
     */
    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        throw new ForbiddenResponse("Function has no implementation");
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param context Context
     * @param s url parameter
     */
    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        throw new ForbiddenResponse("Function has no implementation");
    }
}
