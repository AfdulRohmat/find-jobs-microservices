package com.find_jobs.kafka_producer_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "find-jobs-microservices-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}