package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.RedirectResponse;
import org.example.dto.CreateGreetingDTO;
import org.example.services.GreetingService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AdminGreetingController implements CrudHandler {
    private GreetingService service;

    public AdminGreetingController(GreetingService service) {
        this.service = service;
    }

    @Override
    public void create(@NotNull Context context) {
        CreateGreetingDTO newGreetings = context.bodyAsClass(CreateGreetingDTO.class);
        int id = service.save(newGreetings.getGreetingText());
        context.header("Location", "http://localhost:8080/greetings/" + id );
        context.status(201);
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        //  Needs Implementation
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        //  Needs Implementation
    }

    @Override
    public void getAll(@NotNull Context context) {
        //  Implementation in GreetingController
        //  Redirect
        try {
            context.res.sendRedirect("http://localhost:8080/greetings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        //  Implementation in GreetingController
    }

}