# eCommerce Project

## Introduction

This is a Java-based command-line interface (CLI) eCommerce application. The application will be primarily built using Java and will utilize a PostgreSQL
database to store product and user information.

**Features**

- The user can create and account or login in to an existing account. After doing so a session will be created with relevant information, such as the user_id.
- The user can browse products only when in a session.
-  The user can search for products by name, category, or price range to filter their search down to what they are looking for.
-  The user can add products to their existing cart
-  The user can modify their cart. This includes removal of items or changing the quantity of certain items.
-  The user can checkout and create an order if they go through with the purchase
-  The user can views these order and the items for each order
-  The user can create a review for their purchase items 
-  The user can view ratings and reviews from other users when viewing a product

**Tech Stack**

- **Java**: The main programming language used for building the application.
- **PostgreSQL**: Used as the database to store user, product, and order data.
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **Log4j**: A logging utility for debugging purposes.
- **Lombok**: Used to remove boilerplate code
- **JDBC (Java Database Connectivity)**: An API for connecting and executing queries on the database.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **JUnit, Mockito, and PowerMock**: Used for unit and integration testing.
- **Git and GitHub**: Used for version control.

## Installation Steps
- **Installations**: Make sure to install Maven, Docker, and DBeaver onto your computer. 
- **Set up Docker container**: Execute the following command in your terminal to run a PostgreSQL instance using Docker: 
  ```
  docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
  ```
  You can change the postgres password to your choosing.
- **Set up Dbeaver**: In DBeaver create postgres connection. Username must be postgres, port must be 5432, and password must be the password used to create Docker container. Create a schema (must be lowercase) that you will use to store your data. Create new scripts and copy the ddl and dml under src/main/resources/db/ file path. Then execute these scripts on your schema.
- **Application Properties**: Create an application.properties file under src/main/resources/ file path. Copy the following and replace 'yours' with your info:
  ```
  url=jdbc:postgresql://localhost:5432/postgres?currentSchema=yours
  username=yours
  password=yours
  ```
- **Additional Resources**:
  - Docker Documentation: https://docs.docker.com/
  - Maven Documentation: https://maven.apache.org/guides/index.html
  - DBeaver Documentation: https://dbeaver.io/docs/

## ERD

![entity relationship diagram](https://github.com/052223-java-angular/Navarrete-Soudry-P0/blob/develop/src/main/resources/db/erd.png?raw=true)

