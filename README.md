# LogiFuture Wallet Service

## Overview
LogiFuture Wallet Service is a scalable and fault-tolerant system designed to process user transactions (Debits & Credits) in a gaming wallet platform. The system supports multiple concurrent users while maintaining consistency and performance.


## Problem Statement
Start by understanding the problem described in the [ProblemStatement.mb](docs/ProblemStatement.md)


## Current Progress & Updates
To follow current progress and updates, see the [CHANGELOG.md](CHANGELOG.md)


## Main Features and MVP
The minimum viable product (MVP) of the wallet service includes:

- Debit & Credit transaction processing
- User balance management
- Transaction history logging

For the full list of features and MVP scope, refer to the [MVP.md](docs/mvp.md)


## Tech Stack & Tools
The project uses the following technologies:

- Backend: Spring Boot (Java)
- Database: H2
- Libraries/Tools: JUnit, Spring Data JPA, Lombok, GitHub

For more details, check the [tech_stack.md](docs/tech_stack.md)


## Domain Model
See minimal domain model at the [domain_model.md](docs/domain_model.md)


## Running the Project Locally
To run this service locally, follow these steps:
1. Clone the repository:
```
git clone https://github.com/ohampro/logifuture-wallet-service.git
```

2. Build the project:
```
./mvnw clean install
```

3. Start the application:
```
./mvnw spring-boot:run
```

4. The service should now be running locally. You can access the API at
- ```http://localhost:8080/users/{userId}/balance```
- ```http://localhost:8080/users/{userId}/transactions```
- ```http://localhost:8080/transactions/debit```
- ```http://localhost:8080/transactions/credit```

## Dependencies
- Java 11+
- H2 DB (configured in application.properties)

## Testing
Unit Tests
To run unit tests, use the following command:
```
./mvnw test
```

## Future Improvements
- Add support for high concurrency and fault tolerance.