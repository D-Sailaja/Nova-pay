# NovaPay
A secure digital wallet backend application built by using Java and Spring Boot.
NovaPay enables users to register, authenticate using JWT, manage wallets, transfer money securely, and view transaction history.

# Tech Stack
- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- Maven
- Swagger (OpenAPI)
- Git
- GitHub

# Features
-  User Registration
-  User Login with JWT Authentication
-  Password Encryption using Bcrypt
-  Automatic Wallet Creation
-  Deposit Money
-  Withdraw Money
-  Check Wallet Balance
-  Transfer Money Between Users
-  Transaction History
-  DTO-based API Responses
-  Global Exception Handling
-  Transaction Management using @Transactional
-  Interactive API Documentation with Swagger

## Project Architecture
```text
           API Client (Postman / Swagger)
                    │
                    ▼
     Spring Security +  JWT Authentication
                    │
                    ▼
               REST Controllers
                    │
                    ▼
                 Services
                    │
                    ▼
              Spring Data JPA 
                    │
                    ▼
             Hibernate ORM
                    │
                    ▼
               MySQL Database

```
## Project Structure


```text
src
└── main
    └── java
        └── com.Sailaja .novapay
            ├── config
            ├── security
            ├── controller
            ├── dto
            │   ├── request
            │   └── response
            ├── service
            ├── mapper
            ├── repository
            ├── entity
            ├── exception
            └── enums
```
## API End Points


| Method | Endpoint | Description |
|-------|----------|-------------|
| POST  | `/api/auth/register` | Register a new user |
| POST  | `/api/auth/login` | Authenticate user and generate JWT |
| POST  | `/api/wallet/deposit/{userId}` | Deposit money into wallet |
| POST  | `/api/wallet/withdraw/{userId}` | Withdraw money from wallet |
| GET   | `/api/wallet/balance/{userId}` | Get wallet balance |
| POST  | `/api/wallet/transfer/{senderId}` | Transfer money to another user |
| GET   | `/api/transactions/{userId}` | Retrieve transaction history |

## Database Design

This application uses **MySQL** as the relational database.

### Tables

- **User**
    - Stores user information and authentication details.

- **Wallet**
    - Stores wallet balance for each user.
    - One wallet is associated with one user.

- **Transaction**
    - Stores deposit, withdrawal, and transfer records.
    - Maintains sender, receiver, amount, type, and transaction timestamp.

### Relationships

- One User → One Wallet (`@OneToOne`)
- One User → Many Transactions (`@ManyToOne`)

## Security

NovaPay uses Spring Security and JWT (JSON Web Token) to secure REST APIs.

Security features include:

- JWT-based Authentication
- Password Encryption using BCrypt
- Protected API Endpoints
- Stateless Authentication
- User Role Management

## Getting Started

### Prerequisites

- Java 21 or later
- Maven
- MySQL
- Git

### Installation

1. Clone the repository

```bash
git clone https://github.com/D-Sailaja/Nova-pay.git
```

2. Navigate to the project directory

```bash
cd NovaPay
```

3. Configure MySQL in `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/novapay
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Run the application

```bash
mvn spring-boot:run
```

## API Documentation

NovaPay provides interactive API documentation using Swagger.

After starting the application, open:

```text
http://localhost:8080/swagger-ui/index.html
```
```

Swagger allows you to:

- Register a new user
- Login and obtain a JWT token
- Authorize requests using the JWT token
- Test all secured REST APIs
```




## Future Improvements

- Refresh Token Authentication
- Email Notifications
- Docker Support
- Unit and Integration Testing
- Account Statement Generation

## Author

**Sailaja**

Aspiring Java Backend Developer

GitHub:https://github.com/D-Sailaja