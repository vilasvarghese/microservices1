package com.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	//@Value("${firstName}")
	//private String firstName;
	
	@Autowired
	DBProperties dbProperties;
	
	@RequestMapping("/client")
	public String myClient() {
		return "Client " + dbProperties.getConnection();
	}
}
