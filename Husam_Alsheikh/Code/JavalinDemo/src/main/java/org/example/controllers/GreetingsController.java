package org.example.controllers;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.example.dto.CreateGreetingDTO;
import org.example.services.GreetingService;
import org.jetbrains.annotations.NotNull;

public class GreetingsController implements CrudHandler {
    private GreetingService service;

    public GreetingsController(GreetingService service) {
        this.service = service;
    }

    @Override
    public void create(@NotNull Context context) {
        CreateGreetingDTO newGreetings = context.bodyAsClass(CreateGreetingDTO.class);
        int id = service.save(newGreetings.getGreetingText());

        context.header("Location", "http://localhost:8080/greetings/" + id);
        context.status(201);
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {

    }

    @Override
    public void getAll(@NotNull Context context) {
//        String like = context.queryParam("like");
//        List<GreetingDTO> convertedList = null;
//
//        if(like != null){   //  If like has param, retrieve 'like' results
//            convertedList = greetings
//                    .entrySet()
//                    .stream()
//                    .filter(e -> e.getValue().contains(like))
//                    .map(e -> new GreetingDTO(e.getKey(), e.getValue()))
//                    .collect(Collectors.toList());
//        }
//        else {  //  If like has no param, just retrieve all
//            convertedList = greetings
//                    .entrySet()
//                    .stream()
//                    .map(e -> new GreetingDTO(e.getKey(), e.getValue()))
//                    .collect(Collectors.toList());
//        }
//
//        ListGreetingDTO greetings = new ListGreetingDTO(convertedList);
//
//        //  serialize to JSON
//        context.json(greetings);
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
//        int id = Integer.parseInt(s);
//        String entry = greetings.get(id);
//
//        if(entry != null){
//            GreetingDTO greeting = new GreetingDTO(id, entry);
//            context.json(greeting);
//            return;
//        }
//
//        throw new NotFoundResponse("Greeting with id " + id + " was not found!");
//        //throw new NullPointerException();
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {
//        int id = Integer.parseInt(s);
//        String body = context.body();
//        greetings.put(id, body);
//        context.status(204);
    }
}
