package com.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class FirstController {

	@GetMapping("/message")
	public String test(@RequestHeader("global-request") String header) {
		System.out.println(header);
		return "Hello Called in First Service "+header;
	}
}