# Account API Project

## 🚀 Project Overview.
Spring Boot REST API to upload account data from a text file, search, and update records with optimistic locking and file upload support.

## 🛠 Tech Stack
- Spring Boot 3.5
- PostgreSQL
- Swagger (Springdoc OpenAPI)
- JPA / Hibernate
- Lombok
- JUnit 5 / Mockito

## 🔧 Features
- Upload `.txt` file via API
- Search with pagination
- Update description with version control
- Swagger UI: `http://localhost:8989/swagger-ui.html`
- Unit tests (service + controller)

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

## 🧪 Running Unit Tests
```bash
mvn test
