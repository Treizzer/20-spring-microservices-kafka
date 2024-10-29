package com.microservice.consumer.microservice_consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerListener {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    // With groupId you can form groups of consumers and can share
    // messages between itself, one receive the message and sare it
    @KafkaListener(topics = { "pintura-Topic" }, groupId = "my-group-id")
    public void listener(String message) {
        LOGGER.info("Mensaje recibido, el mensaje es: " + message);
    }

}
