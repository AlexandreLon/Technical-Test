# Technical test for Supralog 

Subject : Create a simple web application to manage a list of users. 
The application have two services :
- the first one to register users
- the second one to list users, find a user by id or by email

## Requirements to run the application

- Java 17 (or higher)
- Maven 3.8.4 (or higher)
- Docker 20.10.12 (or higher)
- Docker-compose 1.29.2 (or higher)

## How to run the application

### Starting mongoDB

- Go to the root of the project
- Create a folder named "data" in the root of the project
- Run the command `docker-compose up --build`
- The database is available on the port 27017
- The database web interface is available on the port 8088

### Run the application

- Go to the root of the project
- Run the command `mvn clean install`
- Go to the folder `user_questioner` and run the command `mvn spring-boot:run`
- Go to the folder `user_registry` and run the command `mvn spring-boot:run`
- The first service is available on the port 8080
- The second service is available on the port 8081

## How to use the application

### Register a user

- POST http://localhost:8080/users
- Body : 
```json
{
  "address": "1 rue de France",
  "birthdate": "1990-01-01",
  "confirmPassword": "P@ss0ord",
  "countryCode": "FR",
  "email": "john.doe@admin.com",
  "firstname": "John",
  "lastname": "Doe",
  "nickname": "JohnDoe",
  "password": "P@ss0ord",
  "phoneNumber": "+33123345678",
  "role": "GUEST"
}
```

### List users

- GET http://localhost:8081/users

### Find a user by id

- GET http://localhost:8081/users/{id}

### Find a user by email

- GET http://localhost:8081/users?email={email}

## How to run the tests

### Run the tests with maven

- Clone the repository
- Go to the root of the project
- Run the command `mvn clean install`
- Go to the folder `user_questioner` and run the command `mvn test`
- Go to the folder `user_registry` and run the command `mvn test`

## Swagger

- The swagger of the first service is available on http://localhost:8080/swagger-ui.html
- The swagger of the second service is available on http://localhost:8081/swagger-ui.html