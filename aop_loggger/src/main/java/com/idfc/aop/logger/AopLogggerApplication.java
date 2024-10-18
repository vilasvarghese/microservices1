package com.idfc.aop.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AopLogggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopLogggerApplication.class, args);
	}

}
