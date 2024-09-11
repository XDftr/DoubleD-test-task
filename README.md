# doubled-test-task

## Project Description

This project is a sample Spring Boot application designed to demonstrate the creation of an API-gateway which dynamically generates forms based on backend responses.

## Table of Contents

- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Usage](#usage)
    - [REST Endpoints](#rest-endpoints)

## Installation

To install and run this project locally, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/XDftr/doubled-test-task
   cd doubled-test-task
   ```
   
2. **Build the application:**
   ```bash
   ./gradlew clean build
   ```

## Running the Application

To run the application, use the following command:

```bash
./gradlew bootRun
```

Once the application is up and running, you can access the H2 console at: `http://localhost:8080/h2-console`

## Usage

### REST Endpoints

#### 1. Get Missing Fields

**URL:** `/api/users/{userId}/missing-fields`  
**Method:** `GET`  
**Description:** Retrieves the list of fields that are required but missing for a specific user. This helps the frontend generate a dynamic form to collect the necessary data from the user.

**Parameters:**
- `userId` (Path Variable): The ID of the user for whom the missing fields are to be retrieved.
- `requiredId` (Query Parameter): The ID of the required fields set.

**Example Response:**
```json
{
  "missingFields": ["birthdate", "birthplace", "sex", "currentAddress"]
}
```

#### 2. Submit User Data Endpoint

**URL:** `/api/users/{userId}/submit`  
**Method:** `POST`  
**Description:** This endpoint accepts the user ID, required ID, and a request body containing user details. It validates the provided data against required fields and submits it to an external service. If any required fields are missing or null, it tries to retrieve them from the existing user details. If they are still missing, an error is thrown.

**Parameters:**
- `userId` (Path Variable): The ID of the user submitting their details.
- `requiredId` (Request Parameter): The ID of the required fields configuration.
- `request` (Request Body): A JSON object containing the user's details.

**Request Body Example:**
```json
{
  "firstName": "John",
  "lastName": "Smith",
  "birthdate": "2024-01-01",
  "birthplace": "Tallinn",
  "sex": "Male",
  "currentAddress": "Tallinn, Estonia"
}
```
