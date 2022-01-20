package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.example.controllers.GreetingController;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static io.javalin.apibuilder.ApiBuilder.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        // create a javalin application with a server and default config
        // listen on port 8080
        Javalin app = Javalin.create().start(8080);

        app.routes(() -> {
            crud("greetings/{id}", new GreetingController());
        });


//        app.routes(() -> {
//            path("hello", () -> greetings.get(1));
//            path("greetings", () -> {
//                get();
//                post();
//                path("{id}", () -> {
//                    get();
//                    put();
//                    delete();
//                });
//            });
//        });

//        // add a handler to the '/' path
//        app.get("/hello", ctx -> ctx.result("Hello, World!"));
//        app.get("/greetings", ctx -> {
//            // convert the map of greetings to a list using the Collections API stream interface
//            List<String> convertedList = greetings
//                    .entrySet()
//                    .stream()
//                    .map(e -> e.getValue())
//                    .collect(Collectors.toList());
//
//            //serialize to JSON
//            ctx.json(convertedList);
//        });
//        app.get("/greetings/{id}", ctx -> {
//            int id = ctx.pathParamAsClass("id", Integer.class).get();
//            String greeting = greetings.get(id);
//            ctx.result(greeting);
//        });
//        app.post("/greetings", ctx -> {
//            String newGreetings = ctx.body();
//            int newId = idGen.getAndIncrement();
//            greetings.put(newId, newGreetings);
//            ctx.header("Location", "http://localhost:8080/greetings/" + newId );
//        });
//
//        app.put("/greetings/{id}"); // will have a body
//        app.delete("/greetings/{id}"); // will not have a body
    }
}
