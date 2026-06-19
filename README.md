# Journal App

A Spring Boot backend application for managing journals with authentication, sentiment analysis, caching, and Kafka messaging.

## Features

- User Authentication using JWT
- Google OAuth Login
- Journal Entry Management
- Weekly Sentiment Analysis
- Kafka Producer & Consumer
- Redis Caching
- Email Notifications
- Swagger/OpenAPI Documentation

## Tech Stack

- Java 21
- Spring Boot
- MongoDB Atlas
- Redis
- Apache Kafka (Confluent Cloud)
- Spring Security
- JWT
- Maven

## API Documentation

After starting the application:

http://localhost:8083/journalapp/swagger-ui/index.html

## Configuration

Copy:

```text
src/main/resources/application-example.yml
```

to:

```text
src/main/resources/application.yml
```

and replace the placeholder values with your own credentials.

## Run Locally

```bash
mvn clean install
mvn spring-boot:run
```

## Author

Banu Siddharth