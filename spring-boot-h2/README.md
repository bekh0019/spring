Spring Boot Security App
============================
Technologies: Spring Boot, Hibernate, Spring Security
Database: H2

The application contains functionality:
* Customized login form
* Custom Error Handling
* DAO-based authentication
* Basic "remember me" authentication
* URL-based security
* Method-level security
* Transactions support
* SonarQube integration

Requirements
------------
* [Java Platform (JDK) 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)

Quick start
-----------
1. mvn spring-boot:run
2. Point your browser to [http://localhost:8080/](http://localhost:8080/)
3. mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=use_your_token_here
4. Check localhost:9000 for report.

