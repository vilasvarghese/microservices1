package com.hsbg.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/chello")
public class HttpsConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	
    @GetMapping
    public String hello() {
        return restTemplate.getForObject("https://localhost:8443/hello", String.class);
    }//Access this as http: http://localhost:8080/chello
}
