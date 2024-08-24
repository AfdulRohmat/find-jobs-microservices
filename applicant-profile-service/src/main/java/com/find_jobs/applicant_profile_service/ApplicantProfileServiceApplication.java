package com.find_jobs.applicant_profile_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApplicantProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicantProfileServiceApplication.class, args);
	}

}
