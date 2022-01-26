package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import org.example.dto.CreateAccountDTO;
import org.example.services.AccountService;
import org.example.services.UserService;
import org.jetbrains.annotations.NotNull;

public class EmpController implements CrudHandler {
    private AccountService accountService;
    private UserService userService;

    public EmpController(AccountService accountService) {
        this.accountService = accountService;
    }

    public EmpController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void create(@NotNull Context context) {  //  Create Account
        CreateAccountDTO newAccount = context.bodyAsClass(CreateAccountDTO.class);

        //  Allow to create a bank account only if username is a known user
        if(userService.getUserByUsername(newAccount.getUsername()) == null){    //  If user does not exist
            throw new ForbiddenResponse("Invalid Username - User does not exist");
        }

        //  Allow to create a bank account only if userid is a known user
        if(userService.getUserByUsername(newAccount.getUsername()).getId() != newAccount.getUserid()){
            throw new ForbiddenResponse("Invalid User ID - User does not exist");
        }

        //  Save account to accountRepo and update account of user
        int accountNum = accountService.save(newAccount.getUserid(), newAccount.getUsername());
        userService.addAccount(accountNum, newAccount.getUserid());

        context.header("Location", "http://localhost:4200/createAccount/" + accountNum);
        context.status(201);
        context.json(accountService.getAccount(accountNum));
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {

    }

    @Override
    public void getAll(@NotNull Context context) {

    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {

    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {

    }
}
