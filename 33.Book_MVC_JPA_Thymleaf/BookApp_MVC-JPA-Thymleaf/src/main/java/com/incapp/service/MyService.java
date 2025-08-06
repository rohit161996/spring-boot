package com.incapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incapp.entity.Book;
import com.incapp.repo.MyRepo;

@Service
public class MyService {
	@Autowired
	MyRepo myRepo;

	public Book getBook(String name) {
		Book b=myRepo.findById(name).orElse(null);
		return b;
	}	
}
