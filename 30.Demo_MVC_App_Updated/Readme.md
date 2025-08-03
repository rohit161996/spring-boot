# Spring Boot Continued

## 1. Addition Controller
- The "ModelMap m" takes the data from the controller to the HTML page.
- We can avoid the use of Autowired if the name attribute of the HTML element and 
  the variable used in the argument of the @RequestParam is same.

```java
@Autowired
AddModel a;

@PostMapping("/add")
public String addition(@RequestParam(name = "num1") int n1, 
					 @RequestParam(name = "num2") int n2, 
					 ModelMap m) {
	a.setNum1(n1);
	a.setNum2(n2);
	int r = a.add();
	m.addAttribute("result", r);
	return "result";
}
```


## 2. Subtraction Controller
#### @ModelAttribute 
- Sprint automatically loads the values of the input form to the Subtract class.
- The "ModelMap m" takes the "result" from the controller to the HTML page.

```java
@PostMapping("/sub")
public String sub(@ModelAttribute Subtract s, ModelMap m) {
	int r = s.sub();
	m.addAttribute("result", r);
	return "result";
}
```


## 3. Common Values Controller:-
#### @ModelAttribute
- It also tells Spring to run this method before any controller method.
- It is used for setting data that should be available to all pages.

#### ModelMap m
- m is used to pass data from the controller to the HTML page.
- Anything added using m.addAttribute(...) will be available in the Thymeleaf template.

#### HttpSession session
- This lets you store data in the user's session.
- It is useful for saving values that should stay available for the entire time the user is using your website.

```java
@ModelAttribute
public void commonValues(ModelMap m, HttpSession session) {
	m.addAttribute("appName", "My First SpringBoot MVC Web App");
	m.addAttribute("devName", "Rahul Chauhan");
	session.setAttribute("cname", "Xyz Abc");
}
```

#### m.addAttribute("appName", "My First SpringBoot MVC Web App");
- Adds the key appName with value "My First SpringBoot MVC Web App" to the page.
- We can display this on the HTML page using:

#### m.addAttribute("devName", "Rahul Chauhan");
- Adds the developer’s name as devName.
- We can use this on any page like this:

#### session.setAttribute("cname", "Xyz Abc");
- Saves "Xyz Abc" as a session value with key cname.
- This is available in all pages until the session ends.
- We can display it on a page.


## 4. HttpSession vs ModelAttribute

```java
@GetMapping("/page1")
public String page1(ModelMap m, HttpSession session) {
	/* Passing the value of the @ModelAttribute */
	Integer c1 = (Integer) m.getAttribute("c1");
	if (c1 == null) {
		c1 = 0;
	}
	m.addAttribute("c1", ++c1);

	/* Passing the value of the HttpSession */
	Integer c2 = (Integer) session.getAttribute("c2");
	if (c2 == null) {
		c2 = 0;
	}
	session.setAttribute("c2", ++c2);

	/* Passing a list to the view */
	List<String> list = new ArrayList<>();
	list.add("Ram");
	list.add("Rahul");
	list.add("Rohan");
	m.addAttribute("data", list);

	return "page1";
}
```

### Description
This Spring Boot controller method is mapped to `/page1` and demonstrates the difference between `ModelMap` and 
`HttpSession` for storing and passing data to the frontend.

### `ModelMap m`
* Used to pass data from the controller to the HTML page for the **current request only**.
* `c1` is a counter stored in `ModelMap`. Its value is incremented on every visit to `/page1`, but **will not persist across requests**.

### `HttpSession session`
* Used to store data across **multiple requests and pages** for the same user session.
* `c2` is a counter that increments and **persists across multiple visits** to `/page1` until the session ends.

### `List<String> data`
* A list of names (`"Ram"`, `"Rahul"`, and `"Rohan"`) is added to `ModelMap` under the key `"data"`.
* In the Thymeleaf HTML page, this list can be displayed using:
```html
<ul>
  <li th:each="name : ${data}" th:text="${name}"></li>
</ul>
```
This method helps demonstrate request vs. session scoped data and passing lists to the frontend.


## 5. Default Mapping to Home Page

```java
@GetMapping(value = { "/", "/home" })
public String home() {
	return "index";
}
```

### Description

* This mapping handles both the root URL `/` and the `/home` path.
* When either of these URLs is accessed, the method returns the `index.html` page.
* Useful for setting a default landing or welcome page for your Spring Boot MVC application.





































