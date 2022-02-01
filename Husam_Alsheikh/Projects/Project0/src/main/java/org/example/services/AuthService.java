package org.example.services;

import io.javalin.core.security.RouteRole;
import org.example.models.User;
import org.example.models.Roles;

import java.util.Set;

public class AuthService {
    private UserService userService;
    private JWTService tokenService;

    public AuthService(UserService userService, JWTService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    /**
     * Method to authenticate users logging in
     * @author Husam Alsheikh
     * @param username username of user trying to authenticate
     * @param password password of user trying to authenticate
     * @return returns token if authenticated, null otherwise
     */
    public String authenticate(String username, String password) {
        User user = userService.getUserByUsername(username);

        if(user == null || !(user.getPassword().equals(password))){
            return null;
        }

        return tokenService.generate(username);
    }

    /**
     * Method to check whether user is authorized
     * @author Husam Alsheikh
     * @param user user to check if authorized
     * @param requiredRoles required roles to have authorization
     * @return returns whether user is authorized or not
     */
    public boolean authorize(User user, Set<RouteRole> requiredRoles) {
        for(Roles role : user.getRoles()) {
            if(requiredRoles.contains(role)) {
                return true;
            }
        }
        return false;
    }
}
