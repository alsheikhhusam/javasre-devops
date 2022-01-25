package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import org.jetbrains.annotations.NotNull;

public class UserController implements CrudHandler {
    @Override
    public void create(@NotNull Context context) {
        //  Implementation in Employee Controller
        throw new ForbiddenResponse("User not authorized");
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        //  Implementation in Employee Controller
        throw new ForbiddenResponse("User not authorized");
    }

    @Override
    public void getAll(@NotNull Context context) {
        throw new ForbiddenResponse("Not Implemented");
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        throw new ForbiddenResponse("Not Implemented");
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {   //  Update balance
        throw new ForbiddenResponse("Not Implemented");
    }
}
