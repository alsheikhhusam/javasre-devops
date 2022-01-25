package org.example;

import io.javalin.Javalin;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.NotFoundResponse;
import io.jsonwebtoken.Claims;
import org.example.controllers.AuthController;
import org.example.controllers.EmpController;
import org.example.dao.*;
import org.example.dto.AccountDTO;
import org.example.models.Employee;
import org.example.models.Roles;
import org.example.models.User;
import org.example.services.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App
{
    public static void main( String[] args )
    {
        Repository<Integer, AccountDTO> accountRepo = new InMemAccountDao();
        AccountService service = new AccountService(accountRepo);

        UserRepository userRepository = new InMemUserRepository();
        UserService userService = new UserService(userRepository);

        EmpRepository empRepository = new InMemEmpRepository();
        EmpService empService = new EmpService(empRepository);

        JWTService tokenService = new JWTService();
        AuthService authService = new AuthService(empService, tokenService);
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

                            Employee emp = empService.getEmpByUsername(username);

                            if(emp == null) {
                                throw new ForbiddenResponse("User unauthorized to perform request");
                            } else {
                                if(authService.authorize(emp, requiredRoles)) {
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
            path("auth", () -> {
                post("login", authController.login);
            });

            crud("createAccount/{id}", new EmpController(service), Roles.EMPLOYEE);
        });
    }
}
