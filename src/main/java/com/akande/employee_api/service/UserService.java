package com.akande.employee_api.service;

import com.akande.employee_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.akande.employee_api.dto.RegisterRequest;
import com.akande.employee_api.enums.Role;
import com.akande.employee_api.exception.UserAlreadyExistsException;
import com.akande.employee_api.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

}