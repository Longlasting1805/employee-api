    # Employee API

A secure RESTful Employee Management API built with Spring Boot 3, MongoDB, Docker, and JWT Authentication.

## Features

- Employee CRUD Operations
- JWT Authentication
- Role-based Authorization
- MongoDB Integration
- Swagger/OpenAPI Documentation
- Docker Support
- Unit & Integration Tests
- Global Exception Handling

---

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data MongoDB
- MongoDB Atlas
- JWT
- Maven
- Docker
- Swagger (OpenAPI)

---

## Project Structure

```
src
 ├── controller
 ├── dto
 ├── exception
 ├── model
 ├── repository
 ├── security
 ├── service
 └── config
```

---

## Running Locally

Clone the project

```bash
git clone https://github.com/Longlasting1805/employee-api.git
```

Navigate into the project

```bash
cd employee-api
```

Run

```bash
./mvnw spring-boot:run
```

---

## Docker

Build

```bash
docker compose up --build
```

Stop

```bash
docker compose down
```

---

## API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## Authentication

Obtain a JWT token from the authentication endpoint.

Include it in every secured request.

```
Authorization: Bearer YOUR_TOKEN
```

---

## Running Tests

```bash
./mvnw test
```

---

## Future Improvements


- Refresh Tokens
- Email Notifications
- Audit Logging

---

## Author

Akande Kehinde

Software Engineer