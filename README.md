Overview
This Blogging Application is developed using Java 21 and Spring Boot. It offers a robust platform for users to create, manage, and interact with blog posts. The application provides secure handling of user information using Spring Security, along with various APIs to handle posts, comments, and user management.

Features
1. User Management
User Registration and Login: Secure user authentication using Spring Security.
Password Encryption and Decryption: User passwords are encrypted before storage using industry-standard algorithms, ensuring secure user data.
Role-based Access Control: Different roles such as Admin and User are managed securely.
2. Blog Posts
Create, Update, and Delete Posts: Authenticated users can create new blog posts and manage their content.
Pagination and Sorting: Posts are paginated and can be sorted by date, popularity, and other criteria using flexible API endpoints.
3. Comments
Comment on Posts: Users can comment on blog posts.
Comment Management: The system allows users to add, delete, or update comments with validation.
4. Input Validation
Hibernate Validator: Input fields such as usernames, passwords, post titles, and content are validated using Hibernate Validation for ensuring data integrity.
5. Pagination and Sorting
Paginated API: Posts and comments are paginated, allowing users to easily navigate through large sets of data.
Sorting: Posts can be sorted by various parameters such as date, relevance, and popularity using custom APIs.
6. API Endpoints
The application exposes several RESTful APIs for interacting with the blog. Key API functionalities include:

User: Register, login, and user management.
Post: CRUD operations on blog posts with validation and sorting.
Comment: Add, update, and delete comments.
Technology Stack
Java 21
Spring Boot
Spring Security for authentication and authorization.
Spring Data JPA for database interaction.
Hibernate for ORM and validation.
MySQL/PostgreSQL as the relational database.
Maven for project management.
Installation and Setup
Prerequisites
JDK 21 or higher
Maven 3.8+
A relational database like MySQL or PostgreSQL
Git
Steps
