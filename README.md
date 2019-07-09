
##**Employee REST server**

This project it is a web application that exposes REST operations for employees. 

####Technologies and tools used
- JDK 1.8:
    - As core Java platform
- Maven:
    - Dependencies management and build plugin
- IntelliJ IDEA:
    - Maximize developer productivity
- Frameworks: 
    - Spring Boot - Makes it easy to create stand-alone, production-grade Spring based Applications.
- Libraries: 
    - H2 - Store employee objects to in-memory database
    - JPA - Create repository implementations automatically for persistence management
    - Lombok - Automatic getter, setter and equals implementations, with annotations
    - Spring HATEOAS - Give the constructs to define a RESTful service, adding links to relevant operations, It makes it possible to evolve REST services over time

####Startup
On startup, the application ingest a file JSON of employees (\resources\json\employees.json) and stores them into H2 in-memory database, and make them available via the GET endpoint along with other CRUD operations.

To boot the application just make sure you have JDK and Maven installed and then run the following command: ``mvn spring-boot:run``


####The API is able to: 
>- **Get employees by ID**
<br/>Return an employee according his ID. 
<br/>Example:
http://localhost:8080/employees/1
<br/>Method: GET


>- **Create new employees**
<br/>Create an employee. 
<br/>Example:
http://localhost:8080/employees/
<br/>Method: POST
<br/>Body: {
	"firstName": "Gerardo", 
	"middleInitial": "M.", 
	"lastName": "Torres", 
	"dateOfBirth": "1995-01-05",
	"dateOfEmployment":"2018-03-01",
	"status": true
}

>- **Update existing employees**
<br/>Update an employee. 
<br/>Example:
http://localhost:8080/employees/2
<br/>Method: PUT
<br/>Body: {
               "id": 2,
               "firstName": "Gerardo",
               "middleInitial": "M.",
               "lastName": "Torres",
               "dateOfBirth": "1995-01-05",
               "dateOfEmployment": "2018-03-01",
               "status": true
           }

>- **Delete employees**
<br/>Change the status of an Employee from active to inactive. This call require a basic authorization header.
<br/>Example:
http://localhost:8080/employees/1
<br/>Method: DELETE
<br/>_Username: admin_
<br/>_Password: password_

>- **Get all employees**
<br/>Return all employees.
<br/>Example:
http://localhost:8080/employees/
<br/>Method: GET

## 
####Design patterns used
- Layered system:
    - **Controllers** -All requests that come for a resource in an application will be handled by a single handler and then dispatched to the appropriate handler for that type of request.
    - **Services** -Organize the services, within a service inventory, into a set of logical layers
    - **Repositories** -Facilitates de-coupling of the business logic and the data access layers in the application
    - **Data Access** -Allow to isolate the application/business layer from the persistence layer
    - **Model** (used across layers) -Data model, it contains no logic describing how to present the data to a user
- **REST** - **HATEOAS** -Model of restful maturity. The hypermedia controls, tell us what we can do next, and the URI of the resource we need to manipulate to do it.
- **Domain Driven Design** -Project's primary focus on the core domain and domain logic. Particular domain problems
- **Exception handler** -The error codes associated with an exception are stored as Static final ints. When the exception is created to be thrown, it is constructed with one of these codes along with an error message. This results in the method that is going to catch it having to look at the code and then decide on a course of action.
- **Security filtering** -Intercept the HTTP request and do the authentication check. General solutions to software security problem.

####Conventions used
- _Convention over configuration_ application setup
- HTTP verbs and response codes - For example if you try and query an employee by GET method, and employee doesn’t exists, API returns message ``Could not find employee {id}`` together with a 404 HTTP status code.
- Naming conventions
    - Classes, attributes and methods
    - RESTful resources naming
    - Maven group and artifact naming
 
##