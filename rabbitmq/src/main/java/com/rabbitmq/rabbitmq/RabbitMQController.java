package com.rabbitmq.rabbitmq;

import javax.crypto.interfaces.DHPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

	@Autowired
	RabbitMQProducer producer;
	
	@Autowired 
	RabbitMQConsumer consumer;
	
	//@PostMapping
	@GetMapping("/publish/{message}")
	public ResponseEntity<?> publish(@PathVariable String message) {
		producer.sendMessage(message);
		return ResponseEntity.ok("Message send to RabbitMQ "+message);
	}
}
