package com.akande.employee_api.service;

import com.akande.employee_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.akande.employee_api.dto.RegisterRequest;
import com.akande.employee_api.enums.Role;
import com.akande.employee_api.exception.UserAlreadyExistsException;
import com.akande.employee_api.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.akande.employee_api.dto.LoginRequest;
import com.akande.employee_api.exception.InvalidCredentialsException;
import com.akande.employee_api.security.JwtService;
import com.akande.employee_api.dto.LoginResponse;
import com.akande.employee_api.dto.UserResponse;
import com.akande.employee_api.dto.UpdateProfileRequest;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            throw new UserAlreadyExistsException(
                    "A user with this email already exists."
            );

        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.EMPLOYEE);

        userRepository.save(user);

        return "User registered successfully.";

    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password.")
                );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            throw new InvalidCredentialsException("Invalid email or password.");

        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);

    }

    public UserResponse getCurrentUser(User user) {

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );

    }

    public UserResponse updateProfile(
            String currentEmail,
            UpdateProfileRequest request
    ) {

        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() ->
                        new InvalidCredentialsException("User not found.")
                );

        if (
                !user.getEmail().equals(request.getEmail()) &&
                        userRepository.findByEmail(request.getEmail()).isPresent()
        ) {

            throw new UserAlreadyExistsException(
                    "Email already exists."
            );

        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );

    }

}