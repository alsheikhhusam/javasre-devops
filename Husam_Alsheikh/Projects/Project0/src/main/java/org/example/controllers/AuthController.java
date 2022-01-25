package org.example.controllers;

import io.javalin.http.UnauthorizedResponse;
import org.example.dto.LoginRequest;
import org.example.dto.LoginResponse;
import org.example.services.AuthService;
import io.javalin.http.Handler;

public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public Handler login = (ctx) -> {
        LoginRequest loginRequest = ctx.bodyAsClass(LoginRequest.class);
        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if(token == null) {
            throw new UnauthorizedResponse("User could not be authenticated");
        }

        ctx.json(new LoginResponse(loginRequest.getUsername(), token));
    };
}
