package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigPropertyController {

	@Autowired
	private DBProperties dbProperties;
	
	@RequestMapping("/properties")
	public String getProperties() {
		return dbProperties.getConnection() + ", "+dbProperties.getHost();
	}
}
