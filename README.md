# 📚 Books REST API

A Spring Boot RESTful API for managing books with full CRUD operations, pagination, filtering, validation, and comprehensive integration testing.

---

## 🚀 Features

- Create, Read, Update, Delete (CRUD) operations
- Pagination support
- Filter books by category
- Input validation using Bean Validation
- Global exception handling
- DTO mapping layer
- Swagger/OpenAPI documentation
- Integration tests using H2 in-memory database

---

## 🏗️ Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (Testing)
- MySQL (Production)
- JUnit 5
- Spring Boot Test (Integration Testing)
- Swagger / OpenAPI

---

## 📦 API Endpoints

| Method | Endpoint | Description |
|-------|---------|-------------|
| GET | /api/books | Get all books (with pagination & category filter) |
| GET | /api/books/{id} | Get book by ID |
| POST | /api/books | Create new book |
| PUT | /api/books/{id} | Update book |
| DELETE | /api/books/{id} | Delete book |

---

## 🧪 Testing

The project includes full integration tests covering:

- CRUD operations
- Pagination & filtering
- Exception handling
- Database validation

Tests use:
- Spring Boot Test
- H2 in-memory database

---

## ⚙️ Running the Project

```bash
mvn spring-boot:run
