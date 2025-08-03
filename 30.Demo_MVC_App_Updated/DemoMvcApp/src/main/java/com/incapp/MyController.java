package com.incapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
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

	@PostMapping("/sub")
	public String sub(@ModelAttribute Subtract s, ModelMap m) {
		int r = s.sub();
		m.addAttribute("result", r);
		return "result";
	}

	@ModelAttribute
	public void commonValues(ModelMap m, HttpSession session) {
		m.addAttribute("appName", "My First SpringBoot MVC Web App");
		m.addAttribute("devName", "Rahul Chauhan");
		session.setAttribute("cname", "Xyz Abc");
	}

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

	@GetMapping(value = { "/", "/home" })
	public String home() {
		return "index";
	}
}
