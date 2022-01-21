package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;
import org.example.controllers.GreetingController;
import org.example.dao.InMemGreetingDao;
import org.example.dao.Repository;
import org.example.dto.ErrorResponse;
import org.example.services.GreetingService;
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

        // create all my dependencies at this level to control how they get used
        // downstream
        Repository<Integer, String> greetingRepo = new InMemGreetingDao();
        GreetingService service = new GreetingService(greetingRepo);


        app.routes(() -> {
            crud("greetings/{id}", new GreetingController(service));
        });

        app.exception(NotFoundResponse.class, (e, ctx) -> {
            ErrorResponse response = new ErrorResponse(e.getMessage(), 404);
            ctx.status(404);
            ctx.json(response);
        });

        app.exception(NullPointerException.class, (e, ctx) -> {
            ErrorResponse response = new ErrorResponse("The devs don't do null checks", 500);
            ctx.status(500);
            ctx.json(response);
        });

        app.exception(Exception.class, (e, ctx) -> {
            ErrorResponse response = new ErrorResponse("Something went wrong, we don't know what!!", 500);
            ctx.status(500);
            ctx.json(response);
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
