# User Service Backend API

This backend API provides functionality to manage user data with various endpoints. It is built with Java 17+, using the Spring Boot framework.

## Table of Contents

1. [Overview](#overview)
2. [API Endpoints](#api-endpoints)
   - [GET /users](#get-users)
   - [GET /users/{userId}](#get-user-by-id)
   - [POST /users](#create-user)
   - [PUT /users/{userId}](#update-user)
   - [DELETE /users/{userId}](#delete-user)
3. [Model](#model)
4. [Validation](#validation)
5. [Response Format](#response-format)
6. [Unit Tests](#unit-tests)
7. [Docker](#docker)
8. [CI/CD](#cicd)

---

## Overview

This API allows you to create, retrieve, update, and delete user data. The API follows RESTful principles and uses JSON format for both request and response bodies. The data is stored in-memory, simulating a real-world database.

The API also performs basic validation for required fields and handles appropriate HTTP response codes for each action.

---

## API Endpoints

### GET /users

Retrieve a list of all users.

#### Response:
- **Status**: `200 OK`
- **Body**: A list of all users.

#### Example Response:
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "username": "john_doe",
    "email": "john@example.com",
    "phone": "123-456-7890",
    "website": "johnswebsite.com",
    "address": { ... },
    "company": { ... }
  }
]
```

---

### GET /users/{userId}

Retrieve details of a specific user by their ID.

#### Parameters:
- `userId` (Path Variable): The ID of the user.

#### Response:
- **Status**: `200 OK` if user found.
- **Status**: `404 Not Found` if user not found.
- **Body**: The user's details.

#### Example Response:
```json
{
  "id": 1,
  "name": "John Doe",
  "username": "john_doe",
  "email": "john@example.com",
  "phone": "123-456-7890",
  "website": "johnswebsite.com",
  "address": { ... },
  "company": { ... }
}
```

---

### POST /users

Create a new user.

#### Request Body:
```json
{
  "name": "John Doe",
  "username": "john_doe",
  "email": "john@example.com",
  "phone": "123-456-7890",
  "website": "johnswebsite.com",
  "address": {
    "street": "123 Main St",
    "suite": "Apt 1",
    "city": "Cityville",
    "zipcode": "12345"
  },
  "company": {
    "name": "John's Company",
    "catchPhrase": "Leading the way",
    "bs": "Innovating"
  }
}
```

#### Response:
- **Status**: `200 OK` on success.
- **Body**: The created user.

---

### PUT /users/{userId}

Update the details of a specific user by their ID.

#### Request Body:
```json
{
  "name": "John Doe Updated",
  "username": "john_doe_updated",
  "email": "john_updated@example.com",
  "phone": "987-654-3210",
  "website": "johnupdated.com",
  "address": {
    "street": "123 Updated St",
    "suite": "Apt 2",
    "city": "New Cityville",
    "zipcode": "67890"
  },
  "company": {
    "name": "John's Updated Company",
    "catchPhrase": "Leading innovation",
    "bs": "Revolutionizing"
  }
}
```

#### Response:
- **Status**: `200 OK` on success.
- **Status**: `404 Not Found` if user not found.

---

### DELETE /users/{userId}

Delete a specific user by their ID.

#### Response:
- **Status**: `204 No Content` on success.
- **Status**: `404 Not Found` if user not found.

---

## Model

The main model for the API is `User`, which contains the following fields:

- **id**: Unique identifier for the user.
- **name**: Name of the user.
- **username**: Username of the user.
- **email**: Email of the user.
- **phone**: Phone number of the user.
- **website**: Website URL of the user.
- **address**: A `Address` object containing the user's address.
- **company**: A `Company` object containing the user's company details.

---

## Validation

The following fields are required when creating or updating a user:

- **name**
- **username**
- **email**

---

## Response Format

All responses from the API will use JSON format with appropriate HTTP status codes (`200 OK`, `404 Not Found`, etc.).

---

## Unit Tests

Unit tests for the API endpoints are implemented using JUnit and Mockito.

---

## Docker

The following `Dockerfile` is provided for packaging the application into a Docker container:

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/userservice.jar /app/userservice.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/userservice.jar"]
```

---

## CI/CD

GitHub Actions workflow for CI/CD:

```yaml
name: CI/CD Pipeline

on:
  push:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout Code
      uses: actions/checkout@v2

    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'

    - name: Build Project
      run: ./mvnw clean install

    - name: Build Docker Image
      run: docker build -t userservice .

    - name: Push Docker Image to Docker Hub
      run: docker push userservice
```

---

## Running the Application Locally

```bash
git clone https://github.com/your-repo/userservice.git
cd userservice
./mvnw clean install
./mvnw spring-boot:run
```

---

