package com.microservice.provider.microservice_provider.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProviderConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();

        // Set the server kafka
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);

        // Which will be the object or key to serialize the message from string to bytes
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Who will serialize the value or Object, StringSerializer
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    // Use the factory pattern to create a provider and send messages
    @Bean
    public ProducerFactory<String, String> providerFactory() {
        return new DefaultKafkaProducerFactory<>(this.producerConfig());
    }

    // This is the Object that will send the messages
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        // You could do this, but is a bad practice, because is a @Bean
        // Instead use dependencies injection (Passing like parameters into function).
        // return new KafkaTemplate<>(this.providerFactory());
        return new KafkaTemplate<>(producerFactory);
    }

}
