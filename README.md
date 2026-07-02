# AI Quiz Analytics System

An intelligent Quiz Management REST API built with **Spring Boot** that allows users to generate quizzes, answer questions, track performance, and analyze quiz statistics.

## Features

### Authentication
- User Registration
- User Login
- JWT Authentication
- Refresh Token Support
- Role-Based Authorization (USER / ADMIN)

### Quiz Management
- Generate quizzes
- Retrieve quiz details
- Submit quiz answers
- View quiz history

### Analytics
- Calculate quiz score
- Track correct and incorrect answers
- Store quiz results
- User performance statistics

### Security
- Spring Security
- JWT Token Authentication
- Password Encryption (BCrypt)
- Protected REST APIs

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT
- Maven
- Lombok
- Swagger / OpenAPI
- Hibernate

## Project Structure

```
src
 ├── config
 ├── security
 ├── auth
 ├── user
 ├── quiz
 ├── analytics
 ├── exception
 ├── mapper
 ├── dto
 └── util
```

## Database

PostgreSQL

Example configuration:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ai_quiz_db
    username: postgres
    password: your_password

  jpa:
    hibernate:
      ddl-auto: update
```

## Installation

Clone the repository

```bash
git clone https://github.com/your-username/ai-quiz-analytics-system.git
```

Move into the project

```bash
cd ai-quiz-analytics-system
```

Build the project

```bash
mvn clean install
```

Run the application

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

## API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI

```
http://localhost:8080/v3/api-docs
```

## Authentication Flow

1. Register a new user
2. Login
3. Receive Access Token and Refresh Token
4. Include the Access Token in the Authorization header

```
Authorization: Bearer <access_token>
```

## Main Endpoints

### Authentication

| Method | Endpoint |
|---------|----------|
| POST | /auth/register |
| POST | /auth/login |
| POST | /auth/refresh |
| POST | /auth/logout |

### Quiz

| Method | Endpoint |
|---------|----------|
| POST | /quiz/generate |
| GET | /quiz/{id} |
| POST | /quiz/{id}/submit |
| GET | /quiz/history |

### Analytics

| Method | Endpoint |
|---------|----------|
| GET | /analytics/me |
| GET | /analytics/{quizId} |

## Future Improvements

- AI-generated questions
- Difficulty levels
- Categories
- Leaderboard
- Email notifications
- Admin dashboard
- Docker support
- Unit & Integration Tests

## Author

**Aynur D**

Backend Developer (Java & Spring Boot)

---

Built with ❤️ using Spring Boot.
