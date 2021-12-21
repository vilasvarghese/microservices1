package com.collaborationservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;




@RestController
@RibbonClient(name="RATING-COLLABORATION", configuration=RibbonConfiguration.class)
public class CollaborationController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/ribbon")
	public String collabHello() {
		return restTemplate.getForObject("http://COMPANY-RATING-COLLABORATION/test", String.class);
	}

}
