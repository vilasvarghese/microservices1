package com.configserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

	@Autowired
	DBProperties dbProperties;
	
	@RequestMapping("/client")
	public String getProperties() {
		return dbProperties.getConnection() +", "+dbProperties.getHost();
	}
}
