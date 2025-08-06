package com.incapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.incapp.entity.Book;
import com.incapp.service.MyService;


@Controller
public class MyController {
	
	@Autowired
	MyService myService;
	
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
	
}
