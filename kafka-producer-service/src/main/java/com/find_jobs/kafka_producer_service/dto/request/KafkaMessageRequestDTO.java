package com.find_jobs.kafka_producer_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaMessageRequestDTO {
    @NotNull(message = "Field cannot be empty!")
    private String topic;

    @NotNull(message = "Field cannot be empty!")
    private String message;
}
