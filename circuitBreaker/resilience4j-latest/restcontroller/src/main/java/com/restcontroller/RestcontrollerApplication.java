package com.restcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestcontrollerApplication {
	public static void main(String[] args) {
		System.out.println("Testing 3");
		ConfigurableApplicationContext context = SpringApplication.run(RestcontrollerApplication.class, args);
	}
}
