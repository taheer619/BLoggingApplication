Overview
This Blogging Application is developed using Java 21 and Spring Boot, providing a feature-rich platform for users to create, manage, and interact with blog posts. The application incorporates robust security, data management, and error handling mechanisms to ensure a seamless user experience.

Features
1. User Management
User Registration and Login: Users can securely sign up and log in using Spring Security.
Password Encryption and Decryption: User passwords are encrypted before storage and securely decrypted during authentication.
API for User Management: APIs are available for managing user-related actions.
2. Blog Posts
Create, Edit, Delete, and View Posts: Users can perform CRUD operations on blog posts.
Pagination and Sorting: Blog posts are paginated and can be sorted by different criteria to improve user experience.
API for Posts: RESTful APIs for managing posts efficiently.
3. Comment Management
Comment on Posts: Users can add comments on blog posts, which can also be managed via APIs.
API for Comments: REST APIs to handle comment operations.
4. Input Validation
Validation: Uses Hibernate Validator to ensure the correctness of input data for users, posts, and comments, such as field lengths, formats, and more.
5. Exception Handling
Custom Exceptions: Custom exceptions are defined to handle various error scenarios in the application.
Global Exception Handler: A Global Exception Handler is implemented to capture and manage exceptions in a unified manner across the application.
6. Security
Spring Security: Implements Spring Security for user authentication and authorization.
Secure Password Management: Passwords are encrypted using industry-standard encryption algorithms.
7. Technology Stack
Java 21
Spring Boot
Spring Security
JPA (Java Persistence API) for database operations
Hibernate for ORM (Object-Relational Mapping)
Spring Data JPA for simplifying data access
RESTful APIs for handling user, post, and comment-related operations
Validation using Hibernate Validator
Custom Exception Handling
Getting Started
Prerequisites
Java 21
Maven or Gradle for dependency management
MySQL (or any relational database)
