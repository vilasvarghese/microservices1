package com.vilas.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(name="COMPANY-RATING-COLLABORATION", configuration=RibbonConfiguration.class)
public class RibbonController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/ribbon")
	public String collabHello() {
		return restTemplate.getForObject("http://COMPANY-RATING-COLLABORATION/test", String.class);
	}
}
