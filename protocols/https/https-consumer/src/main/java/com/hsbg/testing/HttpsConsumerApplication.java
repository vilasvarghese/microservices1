package com.hsbg.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpsConsumerApplication {

	public static void main(String[] args) {
        //System.setProperty("javax.net.ssl.trustStore","D:\\ssl_server.jks");
        //System.setProperty("javax.net.ssl.trustStorePassword","greenlearner");
		SpringApplication.run(HttpsConsumerApplication.class, args);
	}

}
