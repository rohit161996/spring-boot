/* Declares the package for repository classes */
package com.incapp.repo;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.incapp.entities.Book;
import com.incapp.entities.Login;
import jakarta.persistence.EntityManager;

/* Marks this class as a Spring Data repository bean */
@Repository
public class MyRepo {

    /* Injects the container-managed EntityManager */
    @Autowired
    private EntityManager entityManager;

    /* Retrieves a Book entity by its primary key (name) */
    public Book getBook(String name) {
        /* Unwraps Hibernate Session from the EntityManager */
        Session session = entityManager.unwrap(Session.class);
        
        /* Fetches the Book entity with the given name */
        Book book = session.get(Book.class, name);
        
        /* Closes the Hibernate Session */
        session.close();
        
        /* Returns the fetched Book or null if not found */
        return book;
    }

    /* Validates login credentials and retrieves a matching Login entity */
    public Login getLogin(Login login)
    {
        /* Unwraps Hibernate Session from the EntityManager */
        Session session = entityManager.unwrap(Session.class);
        
        /* Fetches the Login entity by its ID */
        Login l = session.get(Login.class, login.getId());
        
        /* Closes the Hibernate Session */
        session.close();
        
        /* Checks if no matching Login was found */
        if (l == null) {
            /* Returns null when no record exists */
            return null;
        } 
        else {
            /* Compares the provided password with the stored password */
            if (login.getPassword().equals(l.getPassword()))
            {
            	/* Returns the Login entity when passwords match */
                return l;
            }
            else
            {
                /* Returns null when passwords do not match */
                return null;
            }
        }
    }
}
