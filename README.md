# Spring Boot CRUD Application

This is a simple Spring Boot CRUD (Create, Read, Update, Delete) application using Spring Data JPA, PostgreSQL, Flyway for database migration, Springdoc OpenAPI for API documentation, and Swagger for interactive API exploration.

## Prerequisites

- Java 17
- Maven
- PostgreSQL (with a database named `crud-db` and user `postgres` with password `6262`)

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/ranasl62/spring-boot-crud.git
2. **Navigate to the project directory:**
    ```bash 
    cd spring-boot-crud
3. **Build the project:**
    ```bash 
    mvn clean install
4. **Run the application:**
    ```bash
    mvn spring-boot:run
The application will be accessible at http://localhost:8080.

## Database Migration
Flyway is used for database migration. Ensure that your PostgreSQL database is running, and Flyway will automatically create the necessary schema and tables on application startup.

## API Documentation
API documentation is generated using Springdoc OpenAPI and Swagger. You can access the Swagger UI for interactive API exploration at http://localhost:8080/swagger-ui/index.html.

## Endpoints
1. **GET /api/users: Retrieve all users.**
2. **GET /api/users/{id}: Retrieve a user by ID.**
3. **POST /api/users: Create a new user.**
4. **PUT /api/users/{id}: Update a user by ID.**
5. **DELETE /api/users/{id}: Delete a user by ID.**


## Testing
**The project includes basic unit tests. You can run the tests using:**

```bash
   mvn test
```
  

  
## Contributing

Feel free to contribute to the project by opening issues or submitting pull requests.

## License
This project is licensed under the MIT License - see the LICENSE file for details.
