# Implementing HATEOAS in Spring Boot REST API

In this branch, i have implemented HATEOAS (Hypermedia as the Engine of Application State) in our Spring Boot REST API. HATEOAS is a constraint of the REST application architecture that keeps the RESTful interface dynamic and discoverable. It allows clients to navigate the API by providing hypermedia links within the responses.

## Launching the Project

This guide assumes you have Java and Maven installed and configured on your system.

### Step 1: Open Your Command Line Interface

Navigate to the root directory of your project where the `pom.xml` file is located. This file contains all Maven project configurations.

### Step 2: Run the Application

Execute the following command in your command line interface:

```bash
mvn spring-boot:run
```
This command compiles your project and starts the Spring Boot application. Maven handles the dependencies, and Spring Boot launches the application on the default port (usually 8080).

### Step 3: Access the Application

Once the application is running, access it via any web browser or API client at: http://localhost:8080

you can interact with your API using the Swagger UI at: http://localhost:8080/swagger-ui/index.html

This page displays the API documentation and provides an interface for testing the API endpoints.



## Example Response

Here is an example response for fetching all delivery methods:

```json
{
  "_embedded": {
    "deliveryMethodDTOList": [
      {
        "id": 1,
        "name": "DRIVE",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/delivery-methods/1"
          },
          "deliveryMethods": {
            "href": "http://localhost:8080/api/delivery-methods"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/delivery-methods"
    }
  }
}
```
In this response, each delivery method includes links to its own resource (self) and the collection of delivery methods (deliveryMethods).

## Benefits of HATEOAS
* **Discoverability**: Clients can discover and navigate the API dynamically without hardcoding the endpoints.
* **Decoupling**: Reduces the dependency between client and server, making it easier to evolve the API.
* **Hypermedia Navigation**: Provides a way to guide clients through the available actions and resources.