# Spring Boot
- MVC Architecture is used by the Spring
- Model View Controller (Architecture) -> WebApp
- Previously it was called Struds.

## Spring Components:-
- Spring has various components.
  - Spring Mail.
  - Spring Security.
  - Spring Webservices.
  - Spring Microservices.

## 3 Tier Architecture:-
- Model -> Java Bean Class (Book, User, Employee)
- View -> React, Thymleaf etc.(Front End)
- Controller -> To which the request goes.

## Servlet
- Multiple servlets handle all the request for their URL call.
- Has the entire logic so not secure.
- Servlet has all the files.


## Controller
- One single file can handle all the control requests.
- Does not have the logic written in it.
- Logic is in the Model File.
- Request is handled by the controller.

## MVC Architecture:-
- Controller only handles request and response.
- Model does the logic handling
- View gives the output to the client.
- These are created independently and then they are combined.

## Microservies:-
- They help in combining the different technologies.

## MVC in SpringBoot:-
- Spring is by default based on the MVC Architecture so we do not have to create the MVC Project Manually.
- Spring will handle everything.
- It will be a lot of pain to create the MVC Application.
- Servlet and JSP based projects are not MVC Based Projects.
- Java Server Pages Containing the Java and HTML is not a good idea.

## How to create the SpringBoot Project:-
1. File -> New -> Spring Starter Project
2. Name	 : 	FirstMVC App / DemoMVC App
3. Type	 : 	Maven
4. Group :	com.rohit
5. NEXT
6. Available :
   Web -> Check the "Spring Web"
7. Available :
   Template -> Check the "Thymleaf"


## Create Files:-
- Create the Model File 	 -> AddModel.java File (Bean Class)
- Create the Controller File -> MyController.java
- Create the View Files		 -> index.html, result.html

## AddModel.java
- It is a normal Java class and when the Spring framework handles the object of this class it is called Bean.
- @Component this annotation tells that the object of the class will be managed by the Spring.

## MyController.java
- @Autowired is a spring annotation which tells the Spring to give the Bean object, we will not create it.
- Spring framework will search for AddModel class, create its object and handle the object.
- @Controller will let the Spring framework know it is a Web Controller Class.
- @PostMapping("/add"): When user submits form (POST request on /add), method executes.
- @RequestParam it puts the data from the form to the Java Variables.


## index.html and result.html
- The data returned from the controller is printed on the front end i.e. result using thymleaf.

