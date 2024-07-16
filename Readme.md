# Delivery Service Backend

This project is built with Spring Boot 3.3.1, Java 21, and H2 database. It provides a backend service for managing delivery methods, booking time slots, and viewing booking history.

## Layers and Descriptions

- **Controller**: Handles incoming HTTP requests, processes them, and returns responses.
    - `BookingController`: Manages endpoints related to booking operations.
    - `DeliveryMethodController`: Manages endpoints related to delivery methods.
    - `TimeSlotController`: Manages endpoints related to time slots.

- **DTO (Data Transfer Object)**: Objects that carry data between processes, encapsulating the data.
    - `BookingDTO`
    - `DeliveryMethodDTO`
    - `TimeSlotDTO`

- **Exception**: Handles exceptions and errors during the application's execution.
    - `CustomException`: Custom exceptions for the application.
    - `GlobalExceptionHandler`: Handles exceptions globally and provides appropriate responses.
    - `ResourceNotFoundException`: Specific exception for resource not found scenarios.

- **Mapper**: Maps entities to DTOs and vice versa, transforming data between layers.
    - `BookingMapper`
    - `DeliveryMethodMapper`
    - `TimeSlotMapper`

- **Model**: Represents the data structure and entities in the application.
    - `Booking`
    - `DeliveryMethod`
    - `TimeSlot`

- **Repository**: Interacts with the database, providing CRUD operations for entities.
    - `BookingRepository`
    - `DeliveryMethodRepository`
    - `TimeSlotRepository`

- **Service**: Contains the business logic of the application, processing data and applying business rules.
    - `BookingService`
    - `DeliveryMethodService`
    - `TimeSlotService`

- **Util**: Contains utility classes and methods used across the application.
    - `DateUtils`

- **DataInitializer**: Initializes the database with default data using a `CommandLineRunner`.

- **DeliveryServiceApplication**: The main class to run the Spring Boot application.

## Application Flow

1. **Application Startup**:
    - Spring Boot initializes all the beans and components.
    - `DataInitializer` runs as a `CommandLineRunner`, initializing the database with default data.

2. **Handling Requests**:
    - When a request hits an endpoint (e.g., `/api/bookings`), it is routed to the corresponding controller method in `BookingController`.
    - The controller method processes the request and calls the appropriate service method (e.g., `BookingService`).
    - The service method performs business logic, interacts with the repository to fetch or save data, and returns a DTO.
    - The controller returns the DTO as a response to the client.

## Running the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```
The application will start on http://localhost:8080 and you can interact with the different endpoints through an API client or a web browser.

## Accessing Swagger
Swagger UI is available at: http://localhost:8080/swagger-ui/index.html
This provides an interface to interact with the API endpoints and view their documentation.
