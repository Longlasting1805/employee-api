package com.akande.employee_api.config;

import com.akande.employee_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import com.akande.employee_api.model.User;
import com.akande.employee_api.enums.Role;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByEmail("admin@employeeapi.com").isPresent()) {
            return;
        }

        User admin = new User();

        admin.setFirstName("System");
        admin.setLastName("Administrator");
        admin.setEmail("admin@employeeapi.com");
        admin.setPassword(passwordEncoder.encode("Admin@123"));
        admin.setRole(Role.ADMIN);

        userRepository.save(admin);

        System.out.println("=====================================");
        System.out.println("ADMIN ACCOUNT CREATED SUCCESSFULLY");
        System.out.println("Email: admin@employeeapi.com");
        System.out.println("Password: Admin@123");
        System.out.println("=====================================");
    }
}