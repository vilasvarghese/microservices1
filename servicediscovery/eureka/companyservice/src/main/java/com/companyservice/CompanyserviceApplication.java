package com.companyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CompanyserviceApplication {

	public static void main(String[] args) {
		System.out.println("Testing 4");
		SpringApplication.run(CompanyserviceApplication.class, args);
	}

}
