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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Properties;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        Properties connectionManagerProps = new Properties();
        connectionManagerProps.setProperty("db.username", "postgres");
        connectionManagerProps.setProperty("db.password", "7841@1487");
        connectionManagerProps.setProperty("db.url", "jdbc:postgresql://35.225.245.98:5432/postgres");

        ConnectionManager connectionManager = new PostgresConnectionManager();
        try {
            connectionManager.init(connectionManagerProps);

            logger.info("Successfully connected to the DB");
        } catch (SQLException throwable) {
            throw new IllegalStateException(throwable);
        }

        Repository<Integer, AccountDTO> accountRepo = new PostgresAccountDao(connectionManager);
        UserRepository userRepo = new PostgresUserDao(connectionManager);

        logger.info("Creating repo instances");

        UserService userService = new UserService(userRepo);
        AccountService accountService = new AccountService(accountRepo);
        JWTService tokenService = new JWTService();
        AuthService authService = new AuthService(userService, tokenService);

        logger.info("Creating services");

        AuthController authController = new AuthController(authService);
        AccountController accountController = new AccountController(accountService, userService);

        logger.info("Creating controllers");

        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.accessManager((handler, context, requiredRoles) -> {
                String header = context.header("Authorization");
                if(requiredRoles.isEmpty()) {
                    handler.handle(context);
                    return;
                }
                if(header == null) {
                    logger.error("This request requires an Authorization header");
                    throw new ForbiddenResponse("This request requires an Authorization header");
                } else {
                    if(!header.startsWith("Bearer ")) {
                        logger.error("This request requires token bearer access");
                        throw new ForbiddenResponse("This request requires token bearer access");
                    } else {
                        String token = header.split(" ")[1];
                        try {
                            Claims claims = tokenService.decode(token);
                            String username = claims.getSubject();

                            User user = userService.getUserByUsername(username);

                            if(user == null) {
                                logger.error("User unauthorized to perform request");
                                throw new ForbiddenResponse("User unauthorized to perform request");
                            } else {
                                if(authService.authorize(user, requiredRoles)) {
                                    //  Store user in cookie store
                                    context.cookieStore("principal", user);

                                    // if we get here the user is authorized
                                    handler.handle(context);

                                } else {
                                    logger.error("User unauthorized to perform request");
                                    throw new ForbiddenResponse("User unauthorized to perform request");
                                }
                            }
                        } catch (Exception ex) {
                            logger.error("The user could not be validated");
                            throw new ForbiddenResponse("The user could not be validated");
                        }
                    }
                }
            });
        }).start(4200);

        app.routes(() -> {
            path("employee", () -> {
                post("login", authController.login);

                crud("createAccount/{id}", new EmpController(accountService, userService), Roles.ROLE_ADMIN);

                path("createAccount", () -> {
                    get(context -> {
                        //  Get user object from cookie store
                        User principal = context.cookieStore("principal");

                        if(!principal.getRoles().contains(Roles.ROLE_ADMIN)){ //  If logged-in user is not employee, throw error
                            logger.error("User unauthorized to perform request");
                            throw new ForbiddenResponse("User unauthorized to perform request");
                        }
                    });
                });
            });

            path("user", () -> {
                post("login", authController.login);

                get("history/userId/{id}", accountController.getTransactionHistory, Roles.ROLE_USER, Roles.ROLE_ADMIN);
            });

            path("accounts", () -> {
                get("viewBalance/accountNum/{acctId}", accountController.getBalance, Roles.ROLE_USER, Roles.ROLE_ADMIN);
                patch("deposit/accountNum/{acctId}", accountController.deposit, Roles.ROLE_USER, Roles.ROLE_ADMIN);
                patch("withdraw/accountNum/{acctId}", accountController.withdraw, Roles.ROLE_USER, Roles.ROLE_ADMIN);
                patch("transfer/userId/{id}", accountController.transfer, Roles.ROLE_USER, Roles.ROLE_ADMIN);
            });
        });
    }
}
