package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Property2Controller {

	
	@Value("${mysurname: Varghese}")//This is the way to define a default value to property.
	private String mysurname;
	
	@RequestMapping("/default")
	public String defaultValue() {
		return mysurname;
	}
}
