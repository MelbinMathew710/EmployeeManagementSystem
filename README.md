Readme.txt file with instructions on how to run the project, set up the database, and provide basic API documentation:


# Employee Management System - Spring Boot Project Readme

This project is a simple Employee Management System implemented in Spring Boot using Couchbase as the NoSQL database.


## Table of Contents
1. [How to Run the Project](#how-to-run-the-project)
2. [Setup the Database](#setup-the-database)
3. [Hosted URL](#hosted-url)
4. [API Documentation](#api-documentation)

---

## How to Run the Project

### Prerequisites
- Java Development Kit (JDK) installed (version 8 or later)
- Apache Maven installed
- Couchbase server installed and running (or CouchDB depending on your choice)

### Steps
1. Clone the repository:
   
   git clone https://github.com/your-username/employee-management-system.git
   cd employee-management-system

2. Build the project:
  
  mvn clean install

3. Run the application:

  java -jar target/employee-management-system.jar

4. The application will start, and you can access it at http://localhost:8080.

### Setup the Database
Couchbase Setup (Example)
Install Couchbase Server: Download Couchbase
Create a bucket named "employee-bucket" with the desired settings.
Configure application.properties with your Couchbase details:

spring.data.couchbase.bootstrap-hosts=localhost
spring.data.couchbase.bucket.name=employee-bucket
spring.data.couchbase.bucket.password=your-bucket-password

### Hosted URL
The application is hosted on Heroku. You can access it at Hosted Employee Management System.

---

## API Documentation
1. Add Employee
Endpoint: POST /employees/addEmployee

Input JSON Structure:
{
  "employeeName": "John Doe",
  "phoneNumber": "+1234567890",
  "email": "john.doe@example.com",
  "reportsTo": "manager-id",
  "profileImage": "https://example.com/john-doe-image.jpg"
}

Output:
{
  "message": "Employee added successfully with ID: generated-employee-id"
}

2. Get All Employees
Endpoint: GET /employees/getAllEmployees
Output:
[
  {
    "id": "employee-id-1",
    "employeeName": "John Doe",
    "phoneNumber": "+1234567890",
    "email": "john.doe@example.com",
    "reportsTo": "manager-id",
    "profileImage": "https://example.com/john-doe-image.jpg"
  },
  // ... (other employees)
]

3. Delete Employee by ID
Endpoint: DELETE /employees/deleteEmployee/{id}

Output:
{
  "message": "Employee with ID employee-id-1 deleted successfully"
}

4. Update Employee by ID
Endpoint: PUT /employees/updateEmployee/{id}

Input JSON Structure:
{
  "employeeName": "Updated Name",
  "phoneNumber": "+9876543210",
  "email": "updated.email@example.com",
  "reportsTo": "new-manager-id",
  "profileImage": "https://example.com/updated-image.jpg"
}

Output:
{
  "message": "Employee with ID employee-id-1 updated successfully"
}

5. Get nth Level Manager of an Employee
Endpoint: GET /employees/getNthLevelManager/{employeeId}/{level}

Output:
{
  "id": "manager-id",
  "employeeName": "Manager Name",
  "phoneNumber": "+9876543210",
  "email": "manager.email@example.com",
  "reportsTo": "grand-manager-id",
  "profileImage": "https://example.com/manager-image.jpg"
}

6. Get Employees with Pagination and Sorting
Endpoint: GET /employees/getEmployeesWithPaginationAndSorting?page={page}&size={size}&sort={sort}

Output:
{
  "content": [
    {
      // Employee details
    },
    // ... (other employees on the page)
  ],
  "totalElements": total-number-of-employees,
  "totalPages": total-number-of-pages,
  "currentPage": current-page-number
}

7. Send Email to Level 1 Manager on New Employee Addition
Endpoint: POST /employees/sendEmailToLevel1Manager?employeeName={employeeName}&employeeName={employeeName}&emailId={emailId}
