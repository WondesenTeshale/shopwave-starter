# ShopWave - SE 4801 Assignment

**Name:** Wendesen Teshale  
**Student Number:** ATE/4671/14

## Project Overview
This is a standard Spring Boot 3.x web application developed for the SE 4801 assignment. It implements a simple REST API for managing a product inventory (creation, retrieval, update, deletion) using an in-memory H2 database.

## Technical Details
- **Java:** 21
- **Framework:** Spring Boot 3.2.4
- **Database:** H2 In-Memory Database
- **Validation:** Spring Boot Starter Validation

## How to Run
1. Ensure you have Java 21 and Maven installed.
2. Open a terminal in the root directory.
3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Access the API on `http://localhost:8080/api/products`.
5. Access the H2 Database Console on `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:shopwavedb`, Username: `se`, Password: `[blank]`).

## Test Suite
The project contains 10 comprehensive tests that achieve full coverage of the repository, service, and controller layers. All tests can be run using:
```bash
mvn clean test
```

## AI Disclosure statement
**Academic Honesty Disclosure:** Portions of this code (variable renaming refactoring, repository testing boilerplate, and global exception mapping) were written with assistance from an AI coding assistant. The logical architecture and structural decisions align directly with the unit lecture fundamentals.
