package org.example;

import io.javalin.Javalin;
import io.javalin.http.ForbiddenResponse;
import io.jsonwebtoken.Claims;
import org.example.controllers.AuthController;
import org.example.controllers.EmpController;
import org.example.controllers.UserController;
import org.example.dao.*;
import org.example.dto.AccountDTO;
import org.example.dto.CreateAccountDTO;
import org.example.models.User;
import org.example.models.Roles;
import org.example.services.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App
{
    public static void main( String[] args )
    {
        Repository<Integer, AccountDTO> accountRepo = new InMemAccountDao();
        AccountService accountService = new AccountService(accountRepo);
        UserRepository userRepository = new InMemUserRepository();
        UserService userService = new UserService(userRepository);

        JWTService tokenService = new JWTService();
        AuthService authService = new AuthService(userService, tokenService);
        AuthController authController = new AuthController(authService);


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

                                    //  Store user in cookie store
                                    context.cookieStore("principal", user);

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
        }).start(4200);

        app.routes(() -> {
            path("employee", () -> {
                post("login", authController.login);

                crud("createAccount/{id}", new EmpController(accountService, userService), Roles.EMPLOYEE);

                path("createAccount", () -> {
                    get(context -> {
                        //  Retrieve username and password from context body and get assign username to string variable
                        CreateAccountDTO tempAccount = context.bodyAsClass(CreateAccountDTO.class);
                        String requestUsername = tempAccount.getUsername();

                        //  Get user object from cookie store
                        User principal = context.cookieStore("principal");

                        if(!principal.getRoles().contains(Roles.EMPLOYEE)){ //  If logged-in user is not employee, throw error
                            throw new ForbiddenResponse("User unauthorized to perform request");
                        }
                    });
                });
            });

            path("user", () -> {
                post("login", authController.login);

                crud("viewBalance/accountNumber/{id}", new UserController(accountService, userService), Roles.USER, Roles.EMPLOYEE);
                crud("deposit/accountNumber/{id}", new UserController(accountService, userService), Roles.USER, Roles.EMPLOYEE);
                crud("withdraw/accountNumber/{id}", new UserController(accountService, userService), Roles.USER, Roles.EMPLOYEE);
                crud("transfer/accountNumber/{id}", new UserController(accountService, userService), Roles.USER, Roles.EMPLOYEE);
                crud("getHistory/userID/{id}", new UserController(accountService, userService), Roles.USER, Roles.EMPLOYEE);
            });

        });
    }
}
