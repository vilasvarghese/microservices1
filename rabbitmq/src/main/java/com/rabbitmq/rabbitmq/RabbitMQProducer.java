package com.rabbitmq.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {


    @Autowired 
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message)
    {
    	System.out.println("sending message "+message);
        rabbitTemplate.convertAndSend(
            "exchange-name", "routing-key", message);
    }
	
}
