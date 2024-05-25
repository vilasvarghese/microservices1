package com.vilaskafkaproducer.kafkaproducer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping ("/api/kafka")
@RestController
public class KafkaController {

	

    final KafkaTemplate kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(KafkaController.class);
    public KafkaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    /*public KafkaController() {
        this.kafkaTemplate = null;
    }*/
    @PostMapping
    public String sentMessage(@RequestBody Account account) {
        this.kafkaTemplate.send("transaction-1", new Account(account.getHolder(), account.getFunds()));
        return "Hello World!";
    }
    @KafkaListener(topics = "transaction-1")
    public void listener(@Payload Account account,  ConsumerRecord<String, Account> cr) {
        logger.info("Topic [transaction-1] Received message from {} with {} PLN ", account.getHolder(), account.getFunds());
        logger.info(cr.toString());
    }
}
