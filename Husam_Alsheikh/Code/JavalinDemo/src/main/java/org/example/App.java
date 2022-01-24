package org.example;

import io.javalin.Javalin;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.NotFoundResponse;
import io.jsonwebtoken.Claims;
import org.example.controllers.AdminGreetingController;
import org.example.controllers.AuthController;
import org.example.controllers.GreetingController;
import org.example.dao.*;
import org.example.dto.ErrorResponse;
import org.example.models.Roles;
import org.example.models.User;
import org.example.services.AuthService;
import org.example.services.GreetingService;
import org.example.services.JWTService;
import org.example.services.UserService;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App 
{
    public static void main( String[] args ) {
        //  Create all dependencies at this level to control how they get used
        //  Downstream dependency injection
        Repository<Integer, String> greetingRepo = new InMemGreetingDao();
        GreetingService service = new GreetingService(greetingRepo);

        UserRepository userRepository = new InMemUserRepository();
        UserService userService = new UserService(userRepository);
        JWTService tokenService = new JWTService();
        AuthService authService = new AuthService(userService, tokenService);
        AuthController authController = new AuthController(authService);

            //  Without Access Management!
        //  Create a javalin application with a server and default config
        //  listen on port 8080
        //Javalin app = Javalin.create().start(8080);

        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.accessManager((handler, context, requiredRoles) -> {
                String header = context.header("Authorization");
                if(requiredRoles.isEmpty()) {
                    System.out.println(requiredRoles);
                    handler.handle(context);
                    return;
                }
                if(header == null) {
                    throw new ForbiddenResponse("This request requires and Authorization header");
                } else {
                    if(!header.startsWith("Bearer ")) {
                        throw new ForbiddenResponse("This request requires token bearer access");
                    } else {
                        String token = header.split(" ")[1];
                        try {
                            Claims claims = tokenService.decode(token);
                            String username = claims.getSubject();

                            User user = userService.getUserByUsername(username);

                            if(user == null) {
                                throw new ForbiddenResponse("User unauthorized to perform request");
                            } else {
                                if(authService.authorize(user, requiredRoles)) {
                                    // if we get here the user is authorized
                                    handler.handle(context);
                                } else {
                                    throw new ForbiddenResponse("User unauthorized to perform request");
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                            throw new ForbiddenResponse("The user could not be validated");
                        }

                    }
                }
            });
        }).start(8080);

        app.routes(() -> {
            //crud("greetings/{id}", new GreetingsController(service));

            crud("greetings/{id}", new GreetingController(service), Roles.USER);
            crud("admin/greetings/{id}", new AdminGreetingController(service), Roles.ADMIN);
            path("auth", () -> {
                post("login", authController.login);
            });
        });

        //  Exception Handling
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
