package com.find_jobs.kafka_producer_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestCustomException extends RuntimeException {

    public BadRequestCustomException(String message) {
        super(message);
    }

    public BadRequestCustomException(String message, Throwable cause) {
        super(message, cause);
    }

}
