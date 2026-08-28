# Spring Boot JPQL Example (URI 2602)

> 🎓 **Course:** This project was developed as part of the **Java Spring Professional** course by **DevSuperior**.

This repository contains a Spring Boot application built to demonstrate data access using **JPQL (Java Persistence Query Language)** and **Spring Data JPA**. The project is configured to connect to a **PostgreSQL** database.

## 🛠 Technologies
* **Java:** 11
* **Framework:** Spring Boot
* **Data Access:** Spring Data JPA / Hibernate
* **Database:** PostgreSQL
* **IDE:** Spring Tool Suite (STS)
* **Build Tool:** Maven

## 🗄️ Database Schema
The application uses a PostgreSQL database with a single `customers` table. 

```sql
CREATE TABLE customers (
  id NUMERIC PRIMARY KEY,
  name CHARACTER VARYING (255),
  street CHARACTER VARYING (255),
  city CHARACTER VARYING (255),
  state CHAR (2),
  credit_limit NUMERIC
);
```
*(Note: You can use the included `2602.sql` file in the project root to seed your database).*

## 🏗️ Project Structure
As seen in the project structure, the project follows a standard layered architecture:

* **`com.devsuperior.uri2602.entities`**: Contains the JPA Entity (`Customer.java`) mapped to the database table.
* **`com.devsuperior.uri2602.repositories`**: Contains the Spring Data JPA repository interface (`CustomerRepository.java`) where the JPQL queries are defined.
* **`com.devsuperior.uri2602.projections`**: Contains interface-based Spring Data Projections (`CustomerMinProjection.java`) to fetch specific columns from the database rather than the entire entity.
* **`com.devsuperior.uri2602.dto`**: Contains Data Transfer Objects (`CustomerMinDTO.java`) used to shape the data returned to the client.

## 🚀 How to Run

1. **Configure the Database:**
   Ensure you have a PostgreSQL instance running. Update the database connection credentials (URL, username, and password) in `src/main/resources/application-dev.properties`.

2. **Build the Project:**
   Navigate to the project root and run:
   ```bash
   ./mvnw clean install
   ```

3. **Run the Application:**
   Start the Spring Boot application using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

## 📝 Query Examples & Execution

This project demonstrates two approaches to fetching data: using **Native SQL with Projections** (`search1`) and using **JPQL with direct DTO instantiation** (`search2`).

The queries are executed directly on startup via the `CommandLineRunner` interface in the main class:

```java
@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// 1. Native SQL Execution + DTO Mapping
		List<CustomerMinProjection> list = repository.search1("rs");
		List<CustomerMinDTO> result1 = list.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());

		System.out.println("\n*** RESULTADO SQL RAIZ:");
		for (CustomerMinDTO obj : result1) {
			System.out.println(obj);
		}

		System.out.println("\n\n");

		// 2. JPQL Execution (Direct DTO Mapping)
		List<CustomerMinDTO> result2 = repository.search2("rs");

		System.out.println("\n*** RESULTADO JPQL");
		for (CustomerMinDTO obj : result2) {
			System.out.println(obj);
		}
	}
}
```
