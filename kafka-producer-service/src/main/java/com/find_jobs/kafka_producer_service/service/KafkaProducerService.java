package com.find_jobs.kafka_producer_service.service;

import com.find_jobs.kafka_producer_service.dto.request.KafkaMessageRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(KafkaMessageRequestDTO kafkaMessageRequestDTO) {
        logger.info(String.format("#### -> Producing message -> %s", kafkaMessageRequestDTO.getMessage()));
        this.kafkaTemplate.send(kafkaMessageRequestDTO.getTopic(), kafkaMessageRequestDTO.getMessage());
    }
}
