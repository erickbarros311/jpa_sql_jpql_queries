# JPQL Query Exercises

> 🎓 **Course:** Developed as part of the **Java Spring Professional** course by **DevSuperior**.

A collection of small, self-contained Spring Boot projects, each solving a data-access exercise from the [beecrowd](https://beecrowd.com/) (formerly URI Online Judge) "JPQL" track. Every subfolder is an independent Maven project that models a different domain, then answers the same question two ways: once with **native SQL** (via a Spring Data **Projection**) and once with **JPQL** (via a constructor-expression **DTO**), so the two approaches can be compared side by side.

## 🛠 Technologies

* **Java 11**
* **Spring Boot** (2.4.x) — Spring Data JPA / Hibernate, Spring Web
* **PostgreSQL** as the runtime database (H2 included for tests)
* **Maven** (with the Maven Wrapper — no local Maven install required)
* **IDE:** Spring Tool Suite (STS) / Eclipse

## 📂 Projects

| Project | beecrowd Problem | Domain | What it queries |
|---|---|---|---|
| [`uri2621`](uri2621) | [2621](https://judge.beecrowd.com/en/problems/view/2621) | Products / Providers | Products within a price range whose provider's name starts with a given prefix |
| [`uri2737`](uri2737) | [2737](https://judge.beecrowd.com/en/problems/view/2737) | Lawyers | Lawyer(s) with the max/min number of customers, plus the average |
| [`uri2990`](uri2990) | [2990](https://judge.beecrowd.com/en/problems/view/2990) | Employees / Departments / Projects | Employees not assigned to any project (`NOT IN` vs. `LEFT JOIN` variants) |

Each project follows the same layered structure:

* **`entities`** — JPA entities mapped to the tables.
* **`projections`** — interface-based Spring Data Projections for native SQL results.
* **`dto`** — DTOs used for JPQL constructor-expression queries and console output.
* **`repositories`** — Spring Data JPA repository with the paired `search1` (native SQL) / `search2` (JPQL) queries (and, for `uri2990`, a third variant).
* Root `*.sql` file — the DDL/DML used to seed the PostgreSQL database for that exercise (`uri2990` also ships an ER diagram, `2990.png`).

Queries run automatically on startup via `CommandLineRunner`, printing the results of each approach to the console.

## 🚀 How to Run a Project

Each project builds and runs independently. From inside the project folder (e.g. `uri2602/`):

1. **Seed the database.** Create a PostgreSQL database matching the name in `application-dev.properties` (e.g. `uri2602`) and run the project's `*.sql` file against it.
2. **Check the credentials** in `src/main/resources/application-dev.properties` match your local PostgreSQL instance.
3. **Build:**
   ```bash
   ./mvnw clean install
   ```
4. **Run:**
   ```bash
   ./mvnw spring-boot:run
   ```
5. Read the query results printed to the console (native SQL result first, JPQL result second).

## 📝 Notes

* Each project is its own Maven module (no parent/aggregator POM) — build and run them individually.
* `uri2602` also has its own nested Git repository.
* `spring.jpa.hibernate.ddl-auto=none` is used throughout, so the schema must be created via the provided `*.sql` file rather than by Hibernate.
