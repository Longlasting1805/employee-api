package com.akande.employee_api.controller;

import com.akande.employee_api.dto.RegisterRequest;
import com.akande.employee_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.akande.employee_api.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {

        return userService.register(request);

    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {

        return userService.login(request);

    }

}