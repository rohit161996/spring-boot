# Spring Web MVC Concepts

## Spring vs Springboot
### Spring 
- It is a framework used to create the Java applications.
- It uses the xml files for configuration which is tough to handle.

### Spring Boot
- It is a tool which makes the development of the Spring application easier.
- It uses the annotations which makes the development easier.

## Project Description:-
- Spring Web MVC Project will be created.                     -> Spring Web MVC ✅
- Spring Data JPA will be used to interact with the Database. -> Spring Data JPA ✅
- Apache Server is configured automatically, springboot has 
  the embedded server which is the apache tomcat's 10th server.

## HIBERNATE --> Replaced with -> JAKARTA PERSISTENCE ANNOTATIONS
- Hibernate's annotations are now replaced with the JPA Annotations.
- Hibernate is an ORM based framework Object Relational Mapping.
- Class is mapped with the database table.

## Spring Data JPA is a Hibernate Based Framework
- It is a component of Spring.
- EntityManager's object is created till now to interact with the database before.
- Now the Spring Data JPA will be used.
- It is a basic project in which most of the stuff is used again.

## Project
- It can be created using the spring initializr -> VS Code.

- Other way is using STS
- File -> New -> Spring Starter Project.
- Name : BookApp_MVC_JPA_Thymleaf
- Type : Maven
- Group: com.rohit
- Java : 17

### Dependencies
- Spring Data JPA
- SpringBoot Dev Tools
- MySQL Driver
- Thymleaf
- Spring Web

## Creating HTML Pages
- They are created in the src/main/resources/templates/
- src/main/resources/templates/index.html

## Server
- Maven Dependencies in the Project Folder
- tomcat-embedded-server.

## Run the Project 
- Right Click on the Project 
- Run as -> Spring Boot App
- Port number displayed on the Console.
- Cannot run twice.
- Relaunch the application.

## Models:-
- Entities made in the database.

## Repo
- Repo is a class having the database code.
- Repository is a place where the code base is present.
- Repository is connected to the database.

## Stage
- Client -> Controller -> Service -> Repository -> Database

## Controller
```java
@PostMapping("/SearchBooks")
public String searchBooks(@RequestParam(name = "name") String name) {
    Book book=myService.getBook(name);
    if(book==null) {
        
    }
    else
    {
        
    }
    return null;
}
```

## Repository
- Create an interface.
- Write the classname we are working on and the type of the primary key we are working on.
```java
@Repository
public interface MyRepo extends JpaRepository<Book, String> {

}
```

## Service
- We will create the MyService class with @Service annotation.
- Spring will create the child class of the MyService class and will write functions of all the crud operations.
- Spring will give functionality for all the crud operations automatically when we autowire the myrepo class.

```java
@Service
public class MyService {
	@Autowired
	MyRepo myRepo;

	public Book getBook(String name) {
		Book b=myRepo.findById(name).orElse(null);
		return b;
	}	
}
```

- Now we will NOT have to write the database code.
- myRepo has given the function findById().
- Convention is to write the name of the function as the request url name.
- High coherency should be there in the code.
- Low coherency will not create a problem altough.

```java
Book b=myRepo.findById(name).orElse(null);
```

It will return an object or return null.