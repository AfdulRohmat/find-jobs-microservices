package com.find_jobs.kafka_producer_service.controller;

import com.find_jobs.kafka_producer_service.dto.request.KafkaMessageRequestDTO;
import com.find_jobs.kafka_producer_service.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaProducerController {

    @Autowired
    private KafkaProducerService producerService;

    @PostMapping("/publish")
    public ResponseEntity<Object> publishMessage(@RequestBody KafkaMessageRequestDTO kafkaMessageRequestDTO) {

        producerService.sendMessage(kafkaMessageRequestDTO);

        return ResponseEntity.ok("Message published successfully to topic : " + kafkaMessageRequestDTO.getTopic());
    }

    @GetMapping(value = "/test",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> getTest() {
        return ResponseEntity.ok("Success Test");
    }
}
