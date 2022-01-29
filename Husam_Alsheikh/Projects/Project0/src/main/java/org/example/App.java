package org.example;

import io.javalin.Javalin;
import io.javalin.http.ForbiddenResponse;
import io.jsonwebtoken.Claims;
import org.example.controllers.AccountController;
import org.example.controllers.AuthController;
import org.example.controllers.EmpController;
import org.example.dao.*;
import org.example.database.ConnectionManager;
import org.example.database.PostgresConnectionManager;
import org.example.dto.AccountDTO;
import org.example.models.User;
import org.example.models.Roles;
import org.example.services.*;

import java.sql.SQLException;
import java.util.Properties;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App
{
    public static void main( String[] args )
    {
        Properties connectionManagerProps = new Properties();
        connectionManagerProps.setProperty("db.username", "postgres");
        connectionManagerProps.setProperty("db.password", "7841@1487");
        connectionManagerProps.setProperty("db.url", "jdbc:postgresql://35.225.245.98:5432/postgres");

        ConnectionManager connectionManager = new PostgresConnectionManager();
        try {
            connectionManager.init(connectionManagerProps);
            System.out.println("Successfully Connected to the DB");
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }

        Repository<Integer, AccountDTO> accountRepo = new InMemAccountDao();
        UserRepository userRepo = new InMemUserRepository();

        UserService userService = new UserService(userRepo);
        AccountService accountService = new AccountService(accountRepo);
        JWTService tokenService = new JWTService();
        AuthService authService = new AuthService(userService, tokenService);

        AuthController authController = new AuthController(authService);
        AccountController accountController = new AccountController(accountService, userService);

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

                get("history/userId/{id}", accountController.getTransactionHistory, Roles.USER, Roles.EMPLOYEE);
            });

            path("accounts", () -> {
                get("viewBalance/accountNum/{acctId}", accountController.getBalance, Roles.USER, Roles.EMPLOYEE);
                patch("deposit/accountNum/{acctId}", accountController.deposit, Roles.USER, Roles.EMPLOYEE);
                patch("withdraw/accountNum/{acctId}", accountController.withdraw, Roles.USER, Roles.EMPLOYEE);
                patch("transfer/userId/{id}", accountController.transfer, Roles.USER, Roles.EMPLOYEE);
            });
        });
    }
}
