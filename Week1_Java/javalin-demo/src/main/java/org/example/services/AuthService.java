package org.example.services;

import org.example.models.Roles;
import org.example.models.User;

import java.util.Arrays;
import java.util.List;


public class AuthService {
    private UserService userService;
    private JWTService tokenService;

    public AuthService(UserService userService, JWTService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public String authenticate(String username, String password) {
        User user = userService.getUserByUsername(username);

        if(user == null || !(user.getPassword().equals(password))) {
            return null;
        }

        String token = tokenService.generate(username);
        return token;
    }

    public boolean authorize(User user, Roles[] requiredRoles) {
        List<Roles> roles = Arrays.asList(requiredRoles);
        for(Roles role : user.getRoles()) {
            if(Arrays.asList(requiredRoles).contains(role)) {
                return true;
            }
        }
        return false;
    }
}
