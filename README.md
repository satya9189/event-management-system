# 🎉 Event Management System

A production-ready **Spring Boot REST API** for managing events, bookings, users, payments, and feedback with secure JWT authentication.

The application follows a layered architecture and is containerized using **Docker** with **MySQL** for easy deployment.

---

## 🚀 Features

### 👤 User Module
- User Registration
- User Login
- JWT Authentication
- Password Encryption (BCrypt)
- Role Based Authorization (USER / ORGANIZER / ADMIN)

### 🎫 Event Module
- Create Event
- Update Event
- Delete Event
- Get Event by Id
- Get All Events
- Pagination & Sorting
- Dynamic Search using JPA Specification

### 🎟 Booking Module
- Book Tickets
- Booking Reference Generation
- Booking Status
- Payment Status
- Booking History

### 💳 Payment Module
- Payment Entity Structure
- Payment Status Tracking

### ⭐ Feedback Module
- Event Feedback Structure
- User Feedback Mapping

### 🔒 Security
- Spring Security
- JWT Authentication
- Method Level Authorization
- Protected REST APIs

### 📖 API Documentation
- Swagger UI
- OpenAPI 3

### 🐳 Docker Support
- Dockerfile
- Docker Compose
- MySQL Container
- Spring Boot Container

---

# 🛠 Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT
- Maven
- Docker
- Docker Compose
- Swagger / OpenAPI

---

# 📂 Project Structure

```
src
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── enums
├── exception
├── mapper
├── repository
├── security
├── service
├── specification

```

---

# 🔐 Authentication

The application uses **JWT Authentication**.

Workflow

```
Register

↓

Login

↓

JWT Token

↓

Authorize

↓

Access Protected APIs
```

---

# 📑 REST APIs

## User APIs

- Register User
- Login User
- Get User By Id
- Update User
- Delete User

## Event APIs

- Create Event
- Update Event
- Delete Event
- Get Event
- Pagination
- Search
- Dynamic Filtering

## Booking APIs

- Create Booking
- Cancel Booking
- Booking History

---

# 🐳 Run Using Docker

Clone the repository

```bash
git clone <repository-url>
```

Move inside project

```bash
cd event-management
```

Run Docker

```bash
docker compose up --build
```

Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📸 Screenshots

> Screenshots will be added soon.

---

# 🔮 Future Enhancements

- React Frontend
- Payment Gateway Integration
- Email Notifications
- Unit Testing
- AWS Deployment
- CI/CD Pipeline

---

# 👨‍💻 Author

**Satyajeet Pandey**

Backend Developer | Java | Spring Boot | Docker
