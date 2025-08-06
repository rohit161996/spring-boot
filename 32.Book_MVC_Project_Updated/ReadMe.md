## Workflows of newly added code:-
### 6. Search Books
- SearchBooks Hyperlink Form on the Main Page which will search according to the name of the book using like query.
- URL Request -> "/SearchBooks" -> Form Action = SearchBooks.
- @PostMapping -> Data is gathered using the @RequestParam.
- getBooks() is called from Repo to get the books from the database using like query.
- List of Books are returned to the controller, then the controller returns the data to the front end using the ModelMap.

#### 1. Controller
```java
@PostMapping("/SearchBooks")
public String searchBooks(@RequestParam String name,ModelMap m) {
    List<Book> books=myRepo.getBooks(name);
    if(books.isEmpty()) {
        m.addAttribute("msg", "Book Not Found!");
        return "index";
    }else {
        m.addAttribute("books", books);
        return "PrintBooks";
    }
}
```

#### 2. Repo Function
```java
public List<Book> getBooks(String name) {
    Session session = entityManager.unwrap(Session.class);
    List<Book> books=session.createQuery("select b from Book b where b.name like :name",Book.class)
    .setParameter("name", "%"+name+"%")
    .list();		
    session.close();
    return books;
}
```

#### 3. Front End
```html
<div th:each="book : ${books}">
    <p>Name: <b th:text="${book.name}"></b> </p>
    <p>Author Name: <b th:text="${book.aname}"></b> </p>
    <br/>
    <form action='BookDetails' method='post'>
        <input type='hidden' name='bname' th:value='${book.name}'>
        <button>View More</button>
    </form>
</div>
```

----------------------------------------------------------------------------------------------------------

### 7. AllBooks
- On the AdminHome page the AllBooks href will will navigate to the AllBooks Controller
- It will check the HTTPSession and allow to view books if the name is present in the session attribute.
- If the session object is not null, the getAllBooks() will be called from the repo.
- The getAllBooks() will get the books from the database using the JPA(Hibernate) session.
- The List of books are returned to the controller then to the front end.

#### 1. Controller
```java
@GetMapping("/AllBooks")
public String allBooks(ModelMap m,HttpSession session) {
    if(session.getAttribute("name")==null) {
        m.addAttribute("msg", "Please Login First!");
        return "admin-login";
    }else {
        List<Book> books=myRepo.getAllBooks();
        if(books.isEmpty()) {
            m.addAttribute("msg", "Book Not Found!");
            return "index";
        }else {
            m.addAttribute("books", books);
            return "AdminPrintBooks";
        }
    }
}
```

#### 2. Repo Function
```java
public List<Book> getAllBooks() {
    Session session= entityManager.unwrap(Session.class);
    List<Book> books=session.createQuery("from Book",Book.class).list();		
    session.close();
    return books;
}
```

#### 3. Front End - AdminPrintBooks.html
```html
<div th:each="book : ${books}">
    <p>Name: <b th:text="${book.name}"></b> </p>
    <p>Author Name: <b th:text="${book.aname}"></b> </p>
    <p>Publisher Name: <b th:text="${book.pname}"></b> </p>
    <p>Price: <b th:text="${book.price}"></b> </p>
    <br/>
    <form action='DeleteBook' method='post'>
        <input type='hidden' name='bname' th:value='${book.name}'>
        <button>Delete Book</button>
    </form>
</div>
```


----------------------------------------------------------------------------------------------------------

### 8. BookDetails
- In the PrintBooks.html a form is added to avoid the display of the URL on the submit button.
- The BookDetails controller is added to map the form submission for View Details.

#### 1. Controller
```java
@PostMapping("/BookDetails")
public String bookDetails(@RequestParam(name = "bname") String bname,ModelMap m) {
    Book book=myRepo.getBook(bname);
    if(book==null) {
        m.addAttribute("msg", "Book Not Found!");
        return "index";
    }else {
        m.addAttribute("book", book);
        return "PrintBookDetails";
    }
}
```

#### 2. Repo Function
- The getBook() used when the SearchBook controller was called is used again.
```java
	public Book getBook(String name) {
		Session session= entityManager.unwrap(Session.class);
		Book book=session.get(Book.class,name);
		session.close();
		return book;
	}
```

#### 3. Front End - AdminPrintBooks.html
- The PrintBookDetails.html is called after the data is returned Repo -> Controller -> HTML Page.

```html
<div>
    <p>Name: <b th:text="${book.name}"></b> </p>
    <p>Author Name: <b th:text="${book.aname}"></b> </p>
    <p>Publisher Name: <b th:text="${book.pname}"></b> </p>
    <p>Price: <b th:text="${book.price}"></b> </p>
</div>
```

