package com.ribbon.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.ribbon.client.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@RestController
public class RibbonOnlyController {
	
	/*@Autowired
	private LoadBalancerClient loadBalancer;
	*/
	@Autowired
	private RestTemplate template;

	@GetMapping("/amazon-payment/{price}")
	public String invokePaymentService(@PathVariable int price) {
		String url = "http://PAYMENT-SERVICE/payment-provider/payNow/" + price;
		//String url = getUrl() + price;
		System.out.println("Entire url "+url);
		
        return template.getForObject(url, String.class);	
    }
	/*
	public String getUrl() {
		ServiceInstance serviceInstance=loadBalancer.choose("PAYMENT-SERVICE");
		System.out.println(serviceInstance.getUri());
		String baseUrl=serviceInstance.getUri().toString();
		baseUrl=baseUrl+"/payment-provider/payNow/";
		return baseUrl;
	}*/

}
