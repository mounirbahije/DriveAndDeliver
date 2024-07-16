## JWT Authentication Endpoints

In this Spring Boot project, JWT authentication is handled through two primary endpoints:

### 1. Sign-In Endpoint
- **Endpoint**: `/api/auth/signin`
- **Method**: POST
- **Description**: Users send their username and password to this endpoint. If the credentials are correct, the server issues a JWT (JSON Web Token) which the user needs for accessing protected routes.

### 2. Sign-Up Endpoint
- **Endpoint**: `/api/auth/signup`
- **Method**: POST
- **Description**: Allows new users to register by providing a username and password. The server stores the user's information and returns a confirmation message.

## Function of JWT in This Project

JWT (JSON Web Token) serves as a compact and secure way to handle user authentication and authorization:

- **Authentication**: During sign-in, if the user's credentials are validated, a JWT is generated and returned to the user. This token includes encoded details like the user's identity (username), issue date, and an expiry time.

- **Authorization**: For subsequent requests to protected endpoints, the user must send this token as part of the request headers. The server validates the token using the `JwtAuthenticationFilter` to ensure it's still valid and corresponds to a valid user.

- **Security**: JWT helps secure the application by ensuring that user credentials are not sent over the network after the initial authentication. This reduces the risk of credentials being intercepted during transmission.

Using JWT enhances the security and usability of the application by enabling stateless authentication and providing a mechanism for users to interact with the app securely after logging in.
