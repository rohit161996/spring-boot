package com.incapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

	@Autowired
	AddModel a;

	@PostMapping("/add")
	public String addition(@RequestParam(name = "num1") int num1,
	                       @RequestParam(name = "num2") int num2,
	                       ModelMap m) {
	    a.setNum1(num1);
	    a.setNum2(num2);
	    int r = a.add();
	    m.addAttribute("sum", r);
	    return "result";
	}

}
