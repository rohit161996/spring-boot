package com.incapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.incapp.entities.Book;
import com.incapp.entities.Login;
import com.incapp.repo.MyRepo;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MyController {
	
	@Autowired
	MyRepo myRepo;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/admin-login")
	public String adminLogin() {
		return "admin-login";
	}
	
	@PostMapping("/SearchBook")
	public String searchBook(@RequestParam(name = "name") String name,
							 ModelMap m) {
		Book book=myRepo.getBook(name);
		if(book==null) {
			m.addAttribute("msg", "Book Not Found!");
			return "index";
		}else {
			m.addAttribute("book", book);
			return "PrintBooks";
		}
	}
	@PostMapping("/AdminLogin")
	public String adminLogin(@ModelAttribute Login login,ModelMap m,HttpSession session) {
		Login l=myRepo.getLogin(login);
		if(l==null) {
			m.addAttribute("msg", "Invalid Credentials!");
			return "admin-login";
		}else {
			session.setAttribute("name", l.getName());
			return "AdminHome";
		}
	}
	
	@RequestMapping("/Logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "admin-login";
	}
	
	@RequestMapping("/AdminHome")
	public String adminHome(ModelMap m,HttpSession session) {
		if(session.getAttribute("name")==null)
		{
			m.addAttribute("msg", "Please Login First!");
			return "admin-login";
		}
		else
		{
			return "AdminHome";
		}
	}
}
