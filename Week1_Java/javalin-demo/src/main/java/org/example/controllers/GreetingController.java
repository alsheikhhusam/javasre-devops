package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.example.dto.CreateGreetingDTO;
import org.example.dto.GreetingDTO;
import org.example.dto.ListGreetingDTO;
import org.example.services.GreetingService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GreetingController implements CrudHandler {
    private GreetingService service;

    public GreetingController(GreetingService service) {
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
