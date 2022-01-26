package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import org.example.dto.AccountDTO;
import org.example.services.AccountService;
import org.example.services.UserService;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class UserController implements CrudHandler {
    private AccountService accountService;
    private UserService userService;

    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    public UserController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void getAll(@NotNull Context context) {

    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        int id = Integer.parseInt(s);   //  Account Number

        Set<String> loggers = userService.getLoggers(); //  Logged in users

        if(loggers.isEmpty()){  //  If no logged-in users throw error
            throw new ForbiddenResponse("No username found - User not authorized");
        }

        AccountDTO accountDTO = accountService.getAccount(id);  //  Get Bank Account based on Account ID

        //  Check to see if account belongs to one of the logged-in users, if not throw error
        if(!loggers.contains(accountDTO.getUsername())){
            throw new ForbiddenResponse("User not authorized to view account balance");
        }

        context.header("Location", "http://localhost:4200/user/viewBalance/" + id);
        context.status(201);
        context.result(String.valueOf(accountDTO.getBalance()));
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        int id = Integer.parseInt(s);   //  Account Number
        int deposit = Integer.parseInt(context.body());

        Set<String> loggers = userService.getLoggers(); //  Logged in users

        if(loggers.isEmpty()){  //  If no logged -in users throw error
            throw new ForbiddenResponse("No username found - User not authorized");
        }

        AccountDTO accountDTO = accountService.getAccount(id);  //  Get Bank Account

        //  Check to see if account belongs to one of the logged-in users, if not throw error
        if(!loggers.contains(accountDTO.getUsername())){
            throw new ForbiddenResponse("User not authorized to deposit");
        }

        accountDTO.setBalance(accountDTO.getBalance() + deposit);
        accountService.updateBalance(accountDTO);


        context.header("Location", "http://localhost:4200/user/deposit/" + id);
        context.status(201);
        context.json(accountService.getAccount(id));
    }

    @Override
    public void create(@NotNull Context context) {
        throw new ForbiddenResponse("User not authorized");
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        throw new ForbiddenResponse("User not authorized");
    }
}
