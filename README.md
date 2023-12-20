# Employee Management System - Spring Boot Project Readme

This project is a Employee Management System implemented in Spring Boot using Couchbase as the NoSQL database.


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
- Couchbase server installed and running

### Steps
1. Clone the repository:
   
   git clone https://github.com/your-username/employee-management-system.git
   cd employee-management-system

2. Run the application:

   a.	Open the project in an Integrated Development Environment (IDE) of your choice, such as IntelliJ IDEA or Eclipse.
   b.	Configure Couchbase connection:
   c.	Open the application.properties file in the project's src/main/resources directory.
   d.	Set the Couchbase connection properties:
   e.	spring.data.couchbase.host=localhost spring.data.couchbase.port=5984 spring.data.couchbase.username=your-username spring.data.couchbase.password=your-password 
      spring.data.couchbase.database=your-database-name 
   f.	Replace your-username, your-password, and your-database-name with the appropriate values for your CouchDB setup.
   g.	Build the project:
   h.	Using Maven: Open a terminal or command prompt, navigate to the project's root directory, and run the following command:
   i.	mvn clean package 
   j.	Run the project:
   k.	Using Maven: In the same terminal or command prompt, run the following command:
   l.	arduinoCopy code
   m.	mvn spring-boot:run 
   n.	The Spring Boot application will start and establish a connection with the Couchbase database.
   

4. The application will start, and you can access it at http://localhost:8080.

### Setup the Database
Couchbase Setup (Example)
Install Couchbase Server: Download Couchbase
Create a bucket named "employee-bucket" with the desired settings.
Configure application.properties with your Couchbase details:

spring.data.couchbase.bootstrap-hosts=localhost
spring.data.couchbase.bucket.name=employee-bucket
spring.data.couchbase.bucket.password=your-bucket-password


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
  "message": "Employee added successfully with ID: {generated-employee-id}"
}

Success Response:
•	HTTP Status Code: 200 OK
•	Response Body: Employee ID
Error Response:
•	HTTP Status Code: 404 Not Found
•	Response Body: Error message if the reporting manager is not found


2. Get All Employees
Endpoint: GET /employees/getAllEmployees

Output:
[
  {
    "employeeName": "John Doe",
    "phoneNumber": "+1234567890",
    "email": "john.doe@example.com",
    "reportsTo": "manager-id",
    "profileImage": "https://example.com/john-doe-image.jpg"
  },
  // ... (other employees)
]

Success Response:
•	HTTP Status Code: 200 OK
•	Response Body: List of EmployeeResponseDto (JSON)
Error Response:
•	HTTP Status Code: 404 Not Found
•	Response Body: Error message if no employees are found


3. Delete Employee by ID
Endpoint: DELETE /employees/deleteEmployee/{id}

Output:
{
  "message": "Employee with ID {id} deleted successfully"
}

Success Response:
•	HTTP Status Code: 200 OK
•	Response Body: Success message
Error Response:
•	HTTP Status Code: 404 Not Found
•	Response Body: Error message if the employee is not found


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
  "message": "Employee with ID {id} updated successfully"
}

Success Response:
•	HTTP Status Code: 200 OK
•	Response Body: Success message

Error Response:
•	HTTP Status Code: 404 Not Found
•	Response Body: Error message if the employee is not found


5. Get nth Level Manager of an Employee
Endpoint: GET /employees/getNthLevelManager/{employeeId}/{level}

Output:
{
  "employeeName": "Manager Name",
  "phoneNumber": "+9876543210",
  "email": "manager.email@example.com",
  "reportsTo": "grand-manager-id",
  "profileImage": "https://example.com/manager-image.jpg"
}

Success Response:
•	HTTP Status Code: 200 OK
•	Response Body: Employee object (JSON)
Error Response:
•	HTTP Status Code: 404 Not Found
•	Response Body: Error message if the employee or manager is not found


6. Get Employees with Pagination and Sorting
Endpoint: GET /employees/getEmployeesWithPaginationAndSorting?page={page}&size={size}&sort={sort}

Output:
[
  {
    "employeeName": "John Doe",
    "phoneNumber": "+1234567890",
    "email": "john.doe@example.com",
    "reportsTo": "manager-id",
    "profileImage": "https://example.com/manager-image.jpg"
  },
  // ... (other employees)
]

Success Response:
•	HTTP Status Code: 200 OK
•	Response Body: List of Employee objects (JSON)
Error Response:
•	HTTP Status Code: 400 Bad Request
•	Response Body: Error message if the request is invalid


7. Send Email to Level 1 Manager on New Employee Addition
Endpoint: POST /employees/sendEmailToLevel1Manager?employeeName={employeeName}&employeeName={employeeName}&emailId={emailId}
