package org.example.services;

import io.javalin.core.security.RouteRole;
import org.example.models.Employee;
import org.example.models.Roles;
import org.example.models.User;

import java.util.Set;

public class AuthService {
    private UserService userService;
    private EmpService empService;
    private JWTService tokenService;

    public AuthService(UserService userService, JWTService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public AuthService(EmpService empService, JWTService tokenService){
        this.empService = empService;
        this.tokenService = tokenService;
    }

    public String authenticate(String username, String password) {
        User user = null;
        Employee emp = null;

        if(empService == null){
            user = userService.getUserByUsername(username);
        }
        else{
            emp = empService.getEmpByUsername(username);
        }

        if(user == null) {
            if(emp == null || !(emp.getPassword().equals(password))){
                return null;
            }

            String token = tokenService.generate(username);
            return token;
        }
        else if(!(user.getPassword().equals(password))){
            return null;
        }

        String token = tokenService.generate(username);
        return token;
    }

    public boolean authorize(User user, Set<RouteRole> requiredRoles) {
        for(Roles role : user.getRoles()) {
            if(requiredRoles.contains(role)) {
                return true;
            }
        }
        return false;
    }

    public boolean authorize(Employee emp, Set<RouteRole> requiredRoles){
        for(Roles role : emp.getRoles()) {
            if(requiredRoles.contains(role)) {
                return true;
            }
        }
        return false;
    }
}
