# 1. Spring-Product

The Spring Product is a project that trains CRUD and API RESTful operations with Spring Boot on a database.

# 2. Technologies

- Language: Java (25+)
- Dependency Manager: Maven (3.9.12+)
- Frameworks: Spring Boot with JPA/Hibernate (4.0.2+) & JUnit (6.0.2+)
- Library: Mockito (5.21.0+)
- Database: PostgreSQL (18.1+) with PgAdmin (4+)
- REST API Client: Postman (11.83.2+)
- GraphQL API Client: GraphiQL (5.2.2+)
- Code Versioning: Git (2.53.0+)
- Native Compilation: GraalVM (25+)
- CI: Github Actions

# 3. Clone the Repository

- Bash

`git clone https://github.com/jose-techcode/Spring-Product`

# 4. Setting Environment Variable

Create a file called .env in the project root and add your database password:

`DB_PASSWORD=your_db_password`

This file should not be uploaded to GitHub, as it contains sensitive information. Therefore, it should be included in .gitignore.

# 5. Setting Spring Rest Docs (.adoc files)

Run ProductApplicationTests.java and this will generate a folder called generated-snippets inside the target folder. You can then use index.adoc.

# 6. Setting GraalVM

- To clean up the target folder and compile the source code for target, run this command:

`mvn clean package`

- After you clean up the target and compile the source code for the target, do the native compilation:

`./mvnw -Pnative native:compile -DskipTests`

- Run the native version:

`./target/Spring-Product`

# 7. Contribution

Feel free to open Issues or submit Pull Requests.

# 8. License

This project is licensed under the AGPL license.