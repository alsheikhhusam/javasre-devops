package org.example;

import io.javalin.Javalin;
import org.example.controllers.GreetingsController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static io.javalin.apibuilder.ApiBuilder.crud;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //create a javalin app with a server and default config
        //listen on port 8080
        Javalin app = Javalin.create().start(8080);

        app.routes(() -> {
           crud("greetings/{id}", new GreetingsController());
        });
/*
        //add a handler to '/main'
        app.get("/main", ctx -> ctx.result("Welcome, to the banking app"));
        app.get("/greetings", ctx -> {
            //convert the map of greetings to a list using the collection API stream interface
            List<String> convertedList = greetings
                .entrySet()
                .stream()
                .map(e -> e.getValue()).collect(Collectors.toList());

            //Serialize to json
            ctx.json(convertedList);
        });

        app.get("/greetings/{id}", ctx -> {
            int id = ctx.pathParamAsClass("id", Integer.class).get();
            String greeting = greetings.get(id);
            ctx.result(greeting);
        });

        app.post("/greeting", ctx -> {
            String newGreetings = ctx.body();
            int newId = idGen.getAndIncrement();
            greetings.put(newId, newGreetings);
            ctx.header("Location", "http://localhost:8080/greetings/" + newId);
        });
        app.put();
        app.delete();
 */   }
}
