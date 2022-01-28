package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import org.example.models.User;
import org.example.services.GreetingService;
import org.jetbrains.annotations.NotNull;

public class GreetingController implements CrudHandler {
    private GreetingService service;

    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @Override
    public void getAll(@NotNull Context context) {
        context.json(service.getAllGreetings());
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {

    }

    @Override
    public void create(@NotNull Context context) {
        throw new ForbiddenResponse("User not authorized");
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        throw new ForbiddenResponse("User not authorized");
    }


    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        throw new ForbiddenResponse("User not authorized");
    }

//    @Override
//    public void getAll(@NotNull Context context) {
//        String like = context.queryParam("like");
//        List<GreetingDTO> convertedList = null;
//
//        if(like != null) {
//            convertedList = greetings.entrySet()
//                    .stream()
//                    .filter(e -> e.getValue().contains(like))
//                    .map(e -> new GreetingDTO(e.getKey(), e.getValue()))
//                    .collect(Collectors.toList());
//        } else {
//            convertedList = greetings
//                    .entrySet()
//                    .stream()
//                    .map(e -> new GreetingDTO(e.getKey(), e.getValue()))
//                    .collect(Collectors.toList());
//        }
//
//        ListGreetingDTO greetings = new ListGreetingDTO(convertedList);
//
//            //serialize to JSON
//            context.json(greetings);
//
//    }
//
//    @Override
//    public void getOne(@NotNull Context context, @NotNull String s) {
//        int id = Integer.parseInt(s);
//        String entry = greetings.get(id);
//
//        if(entry != null) {
//            GreetingDTO greeting = new GreetingDTO(id, entry);
//            context.json((greeting));
//            return;
//        }
//        throw new ArithmeticException();
//    }
//
//    @Override
//    public void update(@NotNull Context context, @NotNull String s) {
//        int id = Integer.parseInt(s);
//        String body = context.body();
//        greetings.put(id, body);
//        context.status(204);
//    }
}
