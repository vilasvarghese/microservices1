package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {
	
	@Value("${firstName}")
	private String myFirstName;

	@Value("${surname}")
	private String lastName;
	

	@GetMapping("/hello")
	public String hello() {
		return myFirstName +" "+ lastName;
	}
}
