package com.incapp.repo;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.incapp.entities.Book;
import com.incapp.entities.Login;

import jakarta.persistence.EntityManager;

@Repository
public class MyRepo {
	@Autowired
	private EntityManager entityManager;
	
	public Book getBook(String name) {
		Session session= entityManager.unwrap(Session.class);
		Book book=session.get(Book.class,name);
		session.close();
		return book;
	}
	
	public Login getLogin(Login login) {
		Session session= entityManager.unwrap(Session.class);
		Login l=session.get(Login.class,login.getId());
		session.close();
		if(l==null) {
			return null;
		}
		else 
		{
			if(login.getPassword().equals(l.getPassword())) 
			{
				return l;
			}
			else 
			{
				return null;
			}
		}
	}
	public List<Book> getBooks(String name) {
		Session session= entityManager.unwrap(Session.class);
		List<Book> books=session.createQuery("select b from Book b where b.name like :name",Book.class)
		.setParameter("name", "%"+name+"%")
		.list();		
		session.close();
		return books;
	}
	
	public List<Book> getAllBooks() {
		Session session= entityManager.unwrap(Session.class);
		List<Book> books=session.createQuery("from Book",Book.class).list();		
		session.close();
		return books;
	}
}
