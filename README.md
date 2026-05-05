# Books REST API

A Spring Boot RESTful API for managing books with full CRUD operations, pagination, filtering, validation, and comprehensive integration testing.


## Features

- Create, Read, Update, Delete (CRUD) operations with Pagination support and Filter books by category
- Input validation using Bean Validation
- Global exception handling
- DTO mapping layer and design pattern
- Swagger/OpenAPI documentation
- testing using H2 in-memory database

## Tools & Technologies

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

## API Endpoints Documentation
- @Get("/api/books") ===============> Get all books (with pagination $ category filter)
- @Get("/api/books/{id}") ==========> Get one book by bookId
- @Post("/api/books") ==============> Create a new book
- @Put("/api/books/{id}") ==========> Update book
- @Delete("/api/books/{id}") =======> Delete book by id


##  Testing 
- CRUD operations
- Pagination & filtering
- Exception handling
- Database validation

