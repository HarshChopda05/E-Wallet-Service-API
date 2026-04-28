# 🚀 E-Wallet Service API

A secure and scalable backend system for managing digital wallets, enabling users to perform deposits, peer-to-peer transfers, and track transactions with full transactional integrity.

---

# 🎯 Objective

The goal of this project is to build a **robust and secure E-Wallet backend system** that:

* Handles user authentication using JWT
* Supports wallet operations (deposit, transfer)
* Maintains transaction history with filtering & pagination
* Ensures **data consistency and transactional safety**
* Follows clean architecture and production-ready practices

---

# ✨ Features

## 🔐 Authentication

* User Registration
* User Login (JWT-based authentication)

## 💰 Wallet Management

* Get Wallet Details
* Add Funds (Deposit)

## 🔄 Transactions

* Peer-to-Peer Money Transfer
* Transaction History (with pagination & filtering)

## ⚙️ System Features

* JPA Auditing (`createdAt`)
* Global Exception Handling
* Swagger API Documentation
* Dockerized Deployment
* Input Validation
* Logging & Debugging support

---

# 🗂️ Database Tables

### 👤 Users

* id
* email
* password
* full_name
* created_at

### 💼 Wallets

* id
* user_id (FK)
* balance
* currency
* created_at

### 💳 Transactions

* id
* sender_wallet_id (FK)
* receiver_wallet_id (FK)
* amount
* type (DEPOSIT / TRANSFER)
* status (SUCCESS / FAILED)
* remarks
* created_at

---

# 🛠️ Tech Stack

* **Backend:** Java, Spring Boot
* **Security:** Spring Security, JWT
* **Database:** MySQL
* **ORM:** Hibernate / JPA
* **Build Tool:** Maven
* **API Docs:** Swagger (OpenAPI)
* **Containerization:** Docker, Docker Compose

---

# 🔄 System Flow

1. User registers → Wallet created automatically
2. User logs in → Receives JWT token
3. User performs operations:

   * Deposit → balance updated
   * Transfer → amount deducted from sender & added to receiver
4. Every transaction is recorded in DB
5. User can fetch transaction history

---

# 📡 API Overview

## 🔐 Auth APIs

* `POST /api/auth/register`
* `POST /api/auth/login`

## 💰 Wallet APIs

* `GET /api/wallet/me`
* `POST /api/wallet/deposit`

## 🔄 Transaction APIs

* `POST /api/transactions/transfer`
* `GET /api/transactions?page=0&size=5&type=TRANSFER`

---

# ⭐ Key Highlights

* 🔐 Secure JWT-based authentication
* ⚡ Optimized database queries with indexing
* 📊 Pagination & filtering support
* 🧱 Clean layered architecture
* 🛡️ Global exception handling
* 📦 Dockerized for easy deployment
* ⏱️ JPA Auditing for tracking transactions

---

# 🔒 Transaction Safety (Important)

To ensure **data consistency and avoid partial updates**, I used:

* `@Transactional` annotation in service layer

### ✔ Guarantees:

* Deduct amount from sender
* Add amount to receiver
* Save transaction record

👉 If any step fails → entire operation is rolled back

This ensures **ACID properties**:

* Atomicity
* Consistency
* Isolation
* Durability

---

# 🧪 Steps to Run Project

## 🔹 Run Locally

1. Start MySQL
2. Create database:

```sql
CREATE DATABASE ewallet;
```

3. Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ewallet
spring.datasource.username=root
spring.datasource.password=your_password
```

4. Run project:

```bash
mvn spring-boot:run
```

---

## 🐳 Run with Docker

```bash
docker-compose down -v
docker-compose up --build
```

---

# 🌐 Access Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

# 🧪 Sample cURL Commands

## 🔐 Register

```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{
  "email": "user@test.com",
  "password": "Password1",
  "fullName": "Test User"
}'
```

---

## 🔐 Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{
  "email": "user@test.com",
  "password": "Password1"
}'
```

---

## 💰 Deposit

```bash
curl -X POST http://localhost:8080/api/wallet/deposit \
-H "Authorization: Bearer <TOKEN>" \
-H "Content-Type: application/json" \
-d '{
  "amount": 500
}'
```

---

## 🔄 Transfer

```bash
curl -X POST http://localhost:8080/api/transactions/transfer \
-H "Authorization: Bearer <TOKEN>" \
-H "Content-Type: application/json" \
-d '{
  "receiverEmail": "other@test.com",
  "amount": 200
}'
```

---

## 📊 Transaction History

```bash
curl -X GET "http://localhost:8080/api/transactions?page=0&size=5&type=TRANSFER" \
-H "Authorization: Bearer <TOKEN>"
```

---

# 📌 Conclusion

This project demonstrates:

* Real-world backend system design
* Secure API development
* Transaction-safe financial operations
* Production-ready deployment using Docker

---

# 👨‍💻 Author

**Harsh Chopda**
Backend Developer (Java + Spring Boot)
