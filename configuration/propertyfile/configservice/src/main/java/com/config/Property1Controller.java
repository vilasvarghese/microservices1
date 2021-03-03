package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Property1Controller {

	@Value("${greeting}")
	private String greeting;
	
	@RequestMapping("/hello1")
	public String hello1() {
		return greeting;
	}
}
