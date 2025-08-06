# BookApp\_MVC - Spring Boot Project Description
This document provides a structured explanation of the `BookApp_MVC` Spring Boot application project. It includes descriptions of packages, files, and the configuration for easy understanding and documentation.



## Project Overview
A Spring Boot MVC project for managing book records and admin authentication, with Thymeleaf used for the frontend.



## Project Creation
- Type : Maven
- Artifact : BookMVCApp
- Package : com.rohit


## Dependencies
- Spring Boot DevTools
- Spring Data JPA
- MySQL Driver
- Thymleaf
- Spring Web


---
## Project Structure
```text
BookApp_MVC
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.incapp
│   │   │       ├── BookAppMvcApplication.java ✅
│   │   │       |
│   │   │       |
│   │   │       ├── controllers (CONTROLLERS) ✅
│   │   │       │   └── MyController.java
│   │   │       |
│   │   │       |
│   │   │       |
│   │   │       ├── entities (MODEL)
│   │   │       │   ├── Book.java (TABLE 1)
│   │   │       │   └── Login.java (TABLE 2)
│   │   │       |
│   │   │       |
│   │   │       └── repo (Layer -> DATABASE CONNECTIONS)
│   │   │           └── MyRepo.java
│   │   |
│   │   |
│   │   └── resources
│   │       ├── static
│   │       |
│   │       ├── templates
│   │       │   ├── AdminHome.html
│   │       │   ├── admin-login.html
│   │       │   ├── index.html
│   │       │   └── PrintBooks.html
│   │       |
│   │       └── application.properties (CONFIGURATIONS)
│   └── test
│       └── java
│
│
├── pom.xml
│
│
└── mvnw / mvnw.cmd
```
---



---------------------------------------------------------------------------------------------------------------------------------------------------


## 1. Application Entry Point 
### - BookAppMvcApplication.java :
- This is the starting point of the application from where the Spring Application runs.


---------------------------------------------------------------------------------------------------------------------------------------------------


## 2. Controllers 
- This is where the requests from the Client comes to and get mapped according to the URL and the type of the request.

### @RequestMapping
- It can be of anytype get or post
```java
@RequestMapping("/")
public String home() {
    return "index";
}
```


### @GetMapping
- Client get the data to the server.
```java
@GetMapping("/admin-login")
public String adminLogin() {
    return "admin-login";
}
```

### @PostMapping
- Form Filling is always a post request.
- Client post the data to the server.
```java
@PostMapping("/SearchBook")
public String searchBook(@RequestParam String name,ModelMap m) {
    Book book=myRepo.getBook(name);
    if(book==null) {
        m.addAttribute("msg", "Book Not Found!");
        return "index";
    }else {
        m.addAttribute("book", book);
        return "PrintBooks";
    }
}
```


---------------------------------------------------------------------------------------------------------------------------------------------------



## 3. Entity -> Model
### - Book.java Description:
* `@Entity`: Marks the class as a JPA entity representing a database table.
* `@Id`: Declares `name` as the primary key (unconventional, usually IDs are numeric).
* `price`, `aname`, `pname`: Store price, author name, and publisher name of the book.
* `image`, `content`: Represent BLOB fields (binary large objects) for storing the book's image and content.
* Getters and setters provide encapsulated access to all fields.
---

### - Login.java Description:
* `@Entity`: Declares this class as a JPA entity for ORM mapping.
* `@Id`: The primary key is `id`, typically representing the username or login identifier.
* `password`, `name`: Both are non-nullable fields for storing user credentials and display name.
* Getters and setters ensure encapsulated data access and modification.


---------------------------------------------------------------------------------------------------------------------------------------------------



## 4. Repo -> It is a layer which interacts with the database.
- It is responsible to connect to the database using the Spring JPA which uses the Hibernate Object Relational Mapping Framework.
- It gets the data based on the already existing methods.


---------------------------------------------------------------------------------------------------------------------------------------------------
## 5. application.properties Configuration

```properties
spring.application.name=BookApp_MVC

server.port=2025

# Optional: Driver class name (usually auto-detected)
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.datasource.url=jdbc:mysql://localhost:3306/bookdb
spring.datasource.username=root
spring.datasource.password=Incapp@12

# Automatically updates database schema to match entities
spring.jpa.hibernate.ddl-auto=update
```

## Description:

* `spring.application.name`: Sets the name of the Spring Boot application.
* `server.port`: Changes the default port from 8080 to 2025.
* `spring.datasource.url`: Defines the MySQL connection URL (database: `bookdb`).
* `spring.datasource.username` and `password`: Database credentials.
* `spring.jpa.hibernate.ddl-auto=update`: Automatically updates the database schema based on entity definitions (suitable for development).

> ⚠️ **Security Note**: Never hardcode credentials like this in production. Use environment variables or secure vault solutions.

---

Let me know if you want explanations for any other specific file or code snippet.




---------------------------------------------------------------------------------------------------------------------------------------------------



## 6. Front End Data Reception :-
- The data is received on the front end using the Thymleaf.



---------------------------------------------------------------------------------------------------------------------------------------------------

## Workflows:-
1. Main Page (Simple HTML Returning)
- URL Request -> "/" -> @RequestMapping -> "index.html" 


2. /SearchBook on the Main Page 
- URL Request -> "/SearchBook" -> Form Action = SearchBook.
- @PostMapping -> Data is gathered using the @RequestParam.
- Method from the Repo is called to get the book from the database using the Primary Key.
- Book is returned to the controller, then the controller returns the data to the front end using the ModelMap.


3. /AdminLogin
- From the index.html page go to admin-login.html using the hyperlink.
- On filling the id and password in the form and Submitting.
- @PostMapping -> Data is gathered using the @ModelAttribute.
- Method from the Repo is called to get the login object.
- If the password matches for the corresponding id(Primary Key) from the database.
- Login object is returned.
- If the object is not null the URL is directed to "admin-home.html" with the session attribute as the name of the admin.
- If the object is null the URL is directed to the "AdminHome".

4. /Logout
- From the AdminHome.html if the user click the Logout hyperlink.
- @PostMapping -> HTTP session is invalidated and admin-login.html is returned.

5. /AdminHome
- Form Submission from the admin-login.html will be directed to the /AdminLogin and it will check the database.
- It will return the AdminHome.html if the entry is found from the repo.
- Otherwise it will stay on the same page i.e. admin-login.html.
- The hyperlink for the AdminHome will be directed to the /AdminHome Mapping.
