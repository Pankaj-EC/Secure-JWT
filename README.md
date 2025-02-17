# Secure JWT with AES Encryption

This project is a Spring Boot application written in Kotlin that incorporates AES encryption with JWT for enhanced security. Key features include an in-memory H2 database for easy setup and testing.

## Prerequisites

Ensure you have the following installed before running the application:

- [Java 17+](https://jdk.java.net/17/)
- [Kotlin](https://kotlinlang.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)

## Getting Started

### Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/Pankaj-EC/Secure-JWT.git
cd Secure-Jwt
```

### Configure H2 Database
Enable H2 Console: To enable the H2 database console, add the following property in your application.properties or application.yml file.

```agsl
In application.properties:
properties
Copy code
spring.h2.console.enabled=true
In application.yml:
yaml
Copy code
spring:
  h2:
    console:
      enabled: true
Run the Application: Start the Spring Boot application from your IDE or via the command line:
```
```bash
Copy code
./gradlew bootRun
or
Copy code
mvn spring-boot:run
Access the H2 Console: Open a web browser and navigate to:
```
```agsl
Copy code
http://localhost:8080/h2-console
Database Connection: Use the following connection details to log in to the H2 Console:
```
```agsl
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: (leave blank)
Run SQL Queries: After logging in, execute SQL queries in the console. For example, to view data in the usrDetails table:

sql
Copy code
SELECT * FROM usrDetails;
AES Encrypted JWT Implementation
Overview
The JwtUtils class in com.user.app.Security manages JWT generation and validation, using AES encryption to secure the tokens further.
```

### Key Functions
Generate JWT: Creates a JWT with AES encryption.
Validate JWT: Decrypts and verifies the token.
Extract Claims: Retrieves user claims from the decrypted token.
Sample Configuration in JwtUtils Class
The class provides encryption and decryption using a predefined AES key (AES_KEY), ensuring the token contents are protected.

### Encryption: AES (ECB mode, PKCS5 padding)
Claims: User ID, channel ID, session ID, and issuer details are included.
To Run the Application
Run the app and check that AES-encrypted JWT tokens are generated, validated, and decrypted correctly. Adjust the AES key (AES_KEY) in JwtUtils if needed for enhanced security.

Useful Commands
Kill Application Port in Windows
To free up the port if already in use:

```bash
Copy code
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```
```agsl
# Note 
private const val SECURE_JWT =true  // to enable security  
private const val SECURE_JWT =false // to decible security  
```

### Conclusion
This project demonstrates secure JWT token management with AES encryption, integrated into a Kotlin Spring Boot application. For further questions or issues, please reach out.# Secure-JWT
