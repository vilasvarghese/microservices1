package com.rabbitmq.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = "queue-name")
    public void receiveMessage(String message)
    {
        // Handle the received message here
        System.out.println("Received message: " + message);
    }
	
}
