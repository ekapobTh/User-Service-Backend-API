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
