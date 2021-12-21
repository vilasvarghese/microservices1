package com.vilas.eurekaproducerforribbon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EurekaproducerforribbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaproducerforribbonApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	
	@RequestMapping("/test")//Run this on different ports
	public String test(){
		return "This server is running on "+port;
	}
}
