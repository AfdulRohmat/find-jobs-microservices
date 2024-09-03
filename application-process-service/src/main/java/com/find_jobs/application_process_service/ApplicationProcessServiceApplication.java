package com.find_jobs.application_process_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
public class ApplicationProcessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationProcessServiceApplication.class, args);
    }

}
