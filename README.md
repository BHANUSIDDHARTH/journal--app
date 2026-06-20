# Journal App

A secure and scalable Journal Management Application built using Spring Boot. Users can create, update, delete, and manage journal entries while leveraging modern backend technologies such as MongoDB, Redis, Kafka, JWT Authentication, Docker, and Swagger.

---

## Features

- User Registration & Authentication
- JWT Based Security
- Create Journal Entries
- Update Journal Entries
- Delete Journal Entries
- MongoDB Integration
- Redis Caching
- Kafka Event Streaming
- Scheduled Weekly Sentiment Analysis
- Email Notifications
- RESTful APIs
- Swagger API Documentation
- Docker Support

---

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data MongoDB
- Spring Data Redis
- Spring Kafka

### Database
- MongoDB Atlas

### Cache
- Redis Cloud

### Messaging
- Apache Kafka
- Confluent Cloud

### Documentation
- Swagger / OpenAPI

### Build Tool
- Maven

### Containerization
- Docker

---

## Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── config
├── scheduler
├── security
├── cache
└── utils
```

---

## API Documentation

After starting the application:

```text
http://localhost:8083/journalapp/swagger-ui/index.html
```

---

## Running Locally

### Clone Repository

```bash
git clone https://github.com/BHANUSIDDHARTH/journal--app.git
cd journal--app
```

### Build Project

```bash
mvn clean package
```

### Run Application

```bash
java -jar target/journalApp-0.0.1-SNAPSHOT.jar
```

---

## Docker

### Build Docker Image

```bash
docker build -t journal-app .
```

### Run Docker Container

```bash
docker run -p 8083:8083 journal-app
```

---

## Configuration

Configure the following services in `application.yml`:

### MongoDB Atlas

```yaml
spring:
  data:
    mongodb:
      uri: YOUR_MONGODB_URI
```

### Redis Cloud

```yaml
spring:
  data:
    redis:
      host: YOUR_REDIS_HOST
      port: YOUR_REDIS_PORT
      password: YOUR_PASSWORD
```

### Kafka

```yaml
spring:
  kafka:
    bootstrap-servers: YOUR_BOOTSTRAP_SERVER
```

---

## Security

The application uses:

- Spring Security
- JWT Authentication
- Role-Based Access Control

---

## Scheduler

A scheduled task runs every Sunday at 7:00 AM to process weekly sentiment-related operations.

---

## Future Improvements

- React Frontend
- CI/CD Pipeline
- Kubernetes Deployment
- Monitoring with Prometheus & Grafana
- AWS Deployment

---

## Author

**D S Bhanu Siddharth**

GitHub:
https://github.com/BHANUSIDDHARTH
