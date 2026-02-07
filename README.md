# 1. Spring-Product

The Spring Product is a project that trains CRUD and API RESTful operations with Spring Boot on a database.

# 2. Technologies

- Language: Java (25+)
- Database: H2 (2.4+)
- Dependency Manager: Maven (3.9+)
- Frameworks: Spring Boot with JPA (4.0.2+) and JUnit (6+)
- Library: Mockito (5.21.0+)
- Code Versioning: Git (2.53.0+)
- CI/CD: Github Actions

# 3. Clone the Repository

- Bash

`git clone https://github.com/jose-techcode/Spring-Product`

# 4. Setting Environment Variables

Create a file called .env in the project root and add your database username:

`DB_USERNAME=your_db_username`

Create a file called .env in the project root and add your database password:

`DB_PASSWORD=your_db_password`

These files should not be uploaded to Github, as they contain sensitive information. Therefore, they should be included in .gitignore.

# 5. Setting Spring Rest Docs (.adoc files)

Run CafeteriaApplicationTests.java and this will generate a folder called target. So, you will can use index.adoc.

# 6. Contribution

Feel free to open Issues or submit Pull Requests.

# 7. License

This project is licensed under the AGPL license.