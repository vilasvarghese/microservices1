package com.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@RequestMapping("/hello")
	public String doSomething(){
		return "Hello World!!!";
	}
}
