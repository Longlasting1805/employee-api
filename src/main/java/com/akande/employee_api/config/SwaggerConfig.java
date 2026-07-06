package com.akande.employee_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI employeeAPI() {

        return new OpenAPI()

                .info(
                        new Info()

                                .title("Employee Management API")

                                .description("REST API for managing employees using Spring Boot and MongoDB")

                                .version("1.0")

                                .contact(
                                        new Contact()
                                                .name("Akande")
                                                .email("akandekehinde1805@gmail.com")
                                )

                );

    }

}