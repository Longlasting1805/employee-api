package com.akande.employee_api.controller;

import com.akande.employee_api.dto.RegisterRequest;
import com.akande.employee_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.akande.employee_api.dto.LoginRequest;
import com.akande.employee_api.dto.LoginResponse;
import com.akande.employee_api.dto.UserResponse;
import com.akande.employee_api.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.akande.employee_api.dto.ChangePasswordRequest;

import org.springframework.security.core.Authentication;
import com.akande.employee_api.dto.UpdateProfileRequest;

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
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        return userService.login(request);

    }

    @GetMapping("/me")
    public UserResponse getCurrentUser(
            @AuthenticationPrincipal User user
    ) {

        return userService.getCurrentUser(user);

    }

    @PutMapping("/me")
    public UserResponse updateProfile(
            Authentication authentication,
            @Valid @RequestBody UpdateProfileRequest request
    ) {

        return userService.updateProfile(
                authentication.getName(),
                request
        );

    }

    @PutMapping("/change-password")
    public String changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordRequest request
    ) {

        return userService.changePassword(
                authentication.getName(),
                request
        );

    }

}