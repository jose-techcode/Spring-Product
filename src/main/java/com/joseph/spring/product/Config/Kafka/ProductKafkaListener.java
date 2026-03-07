package com.joseph.spring.product.Config.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductKafkaListener {

    @KafkaListener(topics = "product", groupId = "com.joseph")
    void listener(String data) {
        System.out.println("Listener received: " + data);
    }
}
