package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GreetingController implements CrudHandler {

    AtomicInteger idGen = new AtomicInteger(1);
    Map<Integer, String> greetings = new HashMap<>();

    public GreetingController() {
        greetings.put(idGen.getAndIncrement(), "Hello, World!");
        greetings.put(idGen.getAndIncrement(), "Good Day!");
        greetings.put(idGen.getAndIncrement(), "Hello, There!");
        greetings.put(idGen.getAndIncrement(), "Hello, John!");
    }

    @Override
    public void create(@NotNull Context context) {
        String newGreetings = context.body();
            int newId = idGen.getAndIncrement();
            greetings.put(newId, newGreetings);
            context.header("Location", "http://localhost:8080/greetings/" + newId );
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {

    }

    @Override
    public void getAll(@NotNull Context context) {
        String like = context.queryParam("like");
        List<String> convertedList = null;

        if(like != null) {
            convertedList = greetings.entrySet()
                    .stream()
                    .filter(e -> e.getValue().contains(like))
                    .map(e -> e.getValue())
                    .collect(Collectors.toList());
        } else {
            convertedList = greetings
                    .entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }

            //serialize to JSON
            context.json(convertedList);

    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        int id = Integer.parseInt(s);
        context.result(greetings.get(id));
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        int id = Integer.parseInt(s);
        String body = context.body();
        greetings.put(id, body);
        context.status(204);
    }
}
