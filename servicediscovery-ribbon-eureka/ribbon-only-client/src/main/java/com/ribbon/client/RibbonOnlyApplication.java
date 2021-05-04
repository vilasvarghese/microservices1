package com.ribbon.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableDiscoveryClient
@RibbonClient(name = "PAYMENT-A-SERVICE",configuration = RibbonConfiguration.class)
public class RibbonOnlyApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		//SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		//return new RestTemplate(factory);
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonOnlyApplication.class, args);
	}
}
