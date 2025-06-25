# 📦 Account API Project
This Spring Boot project provides RESTful APIs to:

Upload account data from a batch text file.

Retrieve account records with pagination and search.

Update account descriptions with concurrency control.

## 🚀 Features
✅ Batch file processing via REST API.

✅ Using Maven as the build and dependency management tool for RESTful API project.

✅ Pagination and dynamic search (by Customer ID, Account Number, Description).

✅ Concurrency handling with optimistic locking (version control).

✅ Clean OOP design using Service, Repository, and DTO patterns.

✅ Unit tests for service and controller layers.

✅ UML documentation: Class Diagram & Activity Diagrams.

✅ Upload `.txt` file via API

✅ Search with pagination

✅ Update description with version control

✅ Swagger UI: `http://localhost:8989/swagger-ui.html`

✅ Unit tests (service + controller)

## 🗂️ Technologies Used
Java 17

Spring Boot 3.5.3

Spring Data JPA

PostgreSQL

JUnit 5 (Unit Testing)

PlantUML (for diagrams)

## 🛠️ Database Design
PostgreSQL account_record table:

sql
account_record (
id SERIAL PRIMARY KEY,
account_number VARCHAR(50),
customer_id VARCHAR(50),
description VARCHAR(255),
version INTEGER
)
version field is used for optimistic locking to handle concurrent updates.

## 📑 API Endpoints
# File Upload
POST /api/accounts/upload
Upload a text file with account data.

Processes and saves each record.

# Retrieve Accounts
GET /api/accounts
Supports pagination.

Search by customerId, accountNumber, or description.

# Update Description
PUT /api/accounts/{id}?description=newDescription
Concurrent update supported via optimistic locking.

## 🎯 Design Patterns Used
Layered Architecture: Clear separation between Controller, Service, Repository.

Repository Pattern: Simplifies data access.

Service Pattern: Handles business logic, easy to maintain and test.

## DTO Pattern: Clean API request/response data transfer.

These patterns ensure clean code, scalability, and testability.

## 📊 Diagrams
### Class Diagram
![Class Diagram](https://github.com/njamal/Account-API-Project/blob/main/class-diagram.png?raw=true)

### Activity Diagram - File Upload
![Activity Diagram - File Upload](https://github.com/njamal/Account-API-Project/blob/main/Activity_Diagram_File_Upload.png?raw=true)

### Activity Diagram - Update Description
![Activity Diagram - Update Description](https://github.com/njamal/Account-API-Project/blob/main/Activity_Diagram_Update_Description_Flow.png?raw=true)

## ✅ Running the Project
1. Setup PostgreSQL database.

2. Configure `application.properties` with your DB credentials.

3. Run the application: `mvn spring-boot:run`

4. Access API via:
    - Swagger UI: `http://localhost:8989/swagger-ui.html`
    - Postman: `http://localhost:8989/api/accounts`

## ✅ Unit Tests
Service layer tested.

Controller layer tested (mocked service layer).

Both success and failure scenarios covered.

## 📦 GitHub Repository
👉 https://github.com/njamal/Account-API-Project

📚 Notes
Make sure PostgreSQL is running and the connection settings in application.properties are correct.

Required Java 17 or higher.

