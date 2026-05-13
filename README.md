# Books REST API

A secure Spring Boot RESTful API for managing books, users, and roles with JWT authentication and role-based authorization.

## Features

    ### Authentication & Security
      - JWT Authentication
      - Custom JWT filter
      - Login & Registration system
      - Password encryption using BCrypt
      - Role-based authorization (USER / AUTHOR / ADMIN)
      - Protected API endpoints
      - Custom unauthorized response handling
    
    ### All Users Features
      - Register new account
      - Login and receive JWT token
      - View current user information
      - Change password
      - Delete own account
        
    ### Admin Features
      - View all users
      - Promote users to AUTHOR
      - Promote users to ADMIN
      - Delete non-admin users
        
    ### Author Features
      - Create books
      - View own books
      - Delete owned books
    
    ### Basic user
      - View all books
      - Rate books
    
    ### Additional Features
      - DTO request/response architecture
      - Bean Validation
      - Global exception handling
      - Swagger/OpenAPI documentation
      - MySQL database integration
      - H2 database testing support
    


## Tools & Technologies

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JJWT)
- Hibernate
- H2 Database (Testing)
- MySQL (Production)
- JUnit 5
- Spring Boot Test (Integration Testing)
- Swagger / OpenAPI

---

## API Endpoints Documentation

  ### Authentication
    - @POST ("/api/auth/register") =============> Register new user
    - @POST ("/api/auth/login") ================> Login and get JWT
  
  ### All Users Endpoints
    - @GET ("/api/users/info") =================> Get current user info
    - @PUT ("/api/users/password") =============> Update password
    - @DELETE("/api/users") ====================> Delete current user

  ### Author Endpoints
    - @POST ("/api/author") ====================> Create new book
    - @GET ("/api/author") =====================> Get author books
    - @DELETE ("/api/author/{title}") ==========> Delete owned book

  ### Admin Endpoints
    - @GET ("/api/admin") ======================> Get all users
    - @PUT ("/api/admin/{id}/promotetoadmin") ==> Promote user to admin
    - @PUT ("/api/admin/{id}/promotetoauthor") => Promote user to author
    - @DELETE("/api/admin/{id}") ===============> Delete user
    
  ### Basic user Endpoint
    - @GET ("/api/books/{title}/{rating}") =====> Rate a book

  ### Public Endpoint
    - @Get("/api/books") =======================> Get all Books
    

- @Get("/api/books") ===============> Get all books (with pagination $ category filter)
- @Get("/api/books/{id}") ==========> Get one book by bookId
- @Post("/api/books") ==============> Create a new book
- @Put("/api/books/{id}") ==========> Update book
- @Delete("/api/books/{id}") =======> Delete book by id

## Future Improvements
  - Refresh Token
  - Docker support
  - Password Reset
  
##  Testing 
- CRUD operations
- Pagination & filtering
- Exception handling
- Database validation

>>>>> Note:  
>> Previous tests were temporarily removed from this branch because of major refactoring and JWT security integration. Updated tests will be added later.
