# Employee API

A production-ready Employee Management REST API built with Spring Boot 3, MongoDB Atlas, JWT Authentication, Role-Based Access Control (RBAC), Docker, and GitHub Actions CI/CD.

---

## 🚀 Features

- JWT Authentication
- Role-Based Access Control (ADMIN & EMPLOYEE)
- Employee CRUD Operations
- Swagger/OpenAPI Documentation
- MongoDB Atlas Integration
- Docker Support
- GitHub Actions CI/CD
- Global Exception Handling
- Input Validation
- Unit & Integration Testing

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 3
- MongoDB Atlas
- Spring Security
- JWT
- Maven
- Docker
- GitHub Actions
- Swagger/OpenAPI

---

## 📂 Project Structure

```text
src
├── config
├── controller
├── dto
├── exception
├── model
├── repository
├── security
├── service
└── advice
```

---

## 🔐 Authentication

The API uses JWT authentication.

Public endpoints:

- POST /api/auth/register
- POST /api/auth/login

Protected endpoints require:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 📚 API Documentation

After starting the application:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🐳 Running with Docker

```bash
docker compose up --build
```

---

## 🧪 Testing

Run all tests:

```bash
./mvnw test
```

or

```bash
mvn test
```

---

## ⚙️ CI/CD

GitHub Actions automatically:

- Builds the application
- Runs all tests
- Verifies every push and pull request

---

## 📸 Screenshots

Coming soon

- Swagger UI
- Login
- Admin Dashboard
- Employee CRUD
- GitHub Actions Passing

---

## 🔮 Future Improvements

- React Admin Dashboard
- Pagination
- Search & Filtering
- File Uploads
- Email Notifications
- Audit Logs

---

## 👨‍💻 Author

**Akande Al-Ameen**

Backend Engineer | Java | Spring Boot | React | MongoDB
