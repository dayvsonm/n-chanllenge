
# N-Challenge API

This project is a REST API built with Spring Boot, using JWT authentication, an in-memory H2 database, interactive documentation via Swagger, and ready to run in a Docker environment.

---

## 🔧 Technologies Used

- Java 17
- Spring Boot 3.4
- Spring Security 6
- H2 Database (in-memory)
- JWT (Authentication)
- Swagger/OpenAPI 3
- Docker and Docker Compose

---

## 🚀 How to Run the Project

### ✅ Prerequisites

- [Docker](https://www.docker.com/) installed
- [Docker Compose](https://docs.docker.com/compose/) installed

---

### 📦 Step-by-Step

1. **Clone the project**

```bash
git clone https://github.com/your-username/n-challenge.git
cd n-challenge
```

2. **Build and start the containers**

```bash
docker-compose up --build
```

3. **Access the application**

- Swagger UI:  
  👉 http://localhost:8080/swagger-ui.html

- H2 Database Console:  
  👉 http://localhost:8080/h2  
  **JDBC URL:** `jdbc:h2:mem:testdb`  
  **Username:** `sa`  
  **Password:** *(leave it blank)*

---

## 🔐 JWT Authentication

### 1. Login at the endpoint `/auth/login`

Example JSON body:

```json
{
  "email": "dayvson@example.com",
  "password": "1234safe"
}
```

### 2. Copy the returned JWT token

### 3. In Swagger UI, click **Authorize** and paste the token:

```
Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...
```

You can now test protected endpoints directly from the Swagger interface.

---

## 🐳 Useful Docker Commands

- Stop the containers:
```bash
docker-compose down
```

- Rebuild the container:
```bash
docker-compose up --build
```

---

## 📁 Package Structure

- `controller/` – API endpoints
- `service/` – Business logic
- `security/` – JWT and authentication config
- `dto/` – Data Transfer Objects (input/output)
- `repository/` – Database access
- `exception/` – Global error handling
- `config/` – Swagger, security, seeder and other configs

---

## 📌 Notes

- The H2 database resets every time the application restarts (in-memory).
---

## 👨‍💻 Author

**Dayvson Mercon**  
