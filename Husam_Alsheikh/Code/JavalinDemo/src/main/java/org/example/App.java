package org.example;

import io.javalin.Javalin;
import org.example.controllers.GreetingsController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App 
{
    public static void main( String[] args ) {
        //  Create a javalin application with a server and default config
        //  listen on port 8080
        Javalin app = Javalin.create().start(8080);

        app.routes(() -> {
            crud("greetings/{id}", new GreetingsController());
        });

        /* Method 2 short but still messy
        AtomicInteger idGen = new AtomicInteger(1); //  Thread safe integer

        Map<Integer, String> greetings = new HashMap<>();

        greetings.put(idGen.getAndIncrement(), "Hello World!!!!");
        greetings.put(idGen.getAndIncrement(), "Good Morning!!!");

        app.routes(() -> {
            path("hello", () -> greetings.get(1));
            get(ctx -> ctx.result("Hello World"));

            path("greetings", () -> {
                get(ctx -> {
                    //  Convert the map of greetings to a list using hte Collections API stream interface
                    List<String> convertedList = greetings
                            .entrySet()
                            .stream()
                            .map(Map.Entry::getValue)
                            .collect(Collectors.toList());

                    //  serialize to JSON
                    ctx.json(convertedList);
                });

                //post();
                path("{id}", () -> {    //  Nesting, same as /greetings/{id}
                   get(ctx -> {
                       //  {id} is a variable and here we retrieve that id and convert it from string to integer
                       int id = ctx.pathParamAsClass("id", Integer.class).get();

                       //   Retrieve greeting using the key to get value from map and output return it (output it)
                       String greeting = greetings.get(id);
                       ctx.result(greeting);
                   });
                    post(ctx -> {
                        //  Send a string in the body of the request
                        String newGreeting = ctx.body();
                        int newId = idGen.getAndIncrement();
                        greetings.put(newId,newGreeting);
                        ctx.header("Location", "http://localhost:8080/greetings/" + newId);
                    });
                });
            });
        });
        */

        /* Method 1 (long and tedious)
        //  Add a handler to '/' path
        app.get("/", ctx -> ctx.result("Hello World"));

        app.get("/greetings", ctx -> {
            //  Convert the map of greetings to a list using hte Collections API stream interface
            List<String> convertedList = greetings
                    .entrySet()
                    .stream()
                    .map(e -> e.getValue())
                    .collect(Collectors.toList());

            //  serialize to JSON
            ctx.json(convertedList);
        });

        app.get("/greetings/{id}", ctx -> {
            //  {id} is a variable and here we retrieve that id and convert it from string to integer
           int id = ctx.pathParamAsClass("id", Integer.class).get();

           //   Retrieve greeting using the key to get value from map and output return it (output it)
           String greeting = greetings.get(id);
           ctx.result(greeting);
        });

        app.post("/greetings", ctx -> {
            //  Send a string in the body of the request
           String newGreeting = ctx.body();
           int newId = idGen.getAndIncrement();
           greetings.put(newId,newGreeting);
           ctx.header("Location", "http://localhost:8080/greetings/" + newId);
        });
        */
    }
}
