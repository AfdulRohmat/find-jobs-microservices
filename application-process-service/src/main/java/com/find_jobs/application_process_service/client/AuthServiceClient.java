package com.find_jobs.application_process_service.client;

import com.find_jobs.application_process_service.config.FeignClientConfig;
import com.find_jobs.application_process_service.dto.response.UserResponseDTO;
import com.find_jobs.application_process_service.entity.User;
import com.find_jobs.application_process_service.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AUTH-SERVICE", path = "/api/v1/auth", configuration = FeignClientConfig.class)
public interface AuthServiceClient {

    @GetMapping("/user")
    Response<User> getUserLogin();

    @GetMapping("/user")
    Response<UserResponseDTO> getUserLoginData();
}
