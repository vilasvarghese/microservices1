package com.vilas.qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.vilas.ch3.annotation.qualifier")
public class Config {
	
	/*@Bean
	public Car getCar() {
		return new MarutiCar();
	}*/

}