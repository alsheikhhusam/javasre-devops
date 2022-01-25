package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.example.dto.CreateAccountDTO;
import org.example.services.AccountService;
import org.jetbrains.annotations.NotNull;

public class EmpController implements CrudHandler {
    private AccountService service;

    public EmpController(AccountService service) {
        this.service = service;
    }

    @Override
    public void create(@NotNull Context context) {
        CreateAccountDTO newAccount = context.bodyAsClass(CreateAccountDTO.class);
        int accountNum = service.save(newAccount.getName());
        context.header("Location", "http://localhost:445/createAccount" + accountNum);
        context.status(201);
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
