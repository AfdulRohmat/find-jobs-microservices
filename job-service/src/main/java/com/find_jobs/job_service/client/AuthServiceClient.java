package com.find_jobs.job_service.client;

import com.find_jobs.job_service.config.FeignClientConfig;
import com.find_jobs.job_service.dto.response.UserResponseDTO;
import com.find_jobs.job_service.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AUTH-SERVICE", path = "/api/v1/auth", configuration = FeignClientConfig.class)
public interface AuthServiceClient {

    @GetMapping("/user")
    Response<UserResponseDTO> getUserLogin();
}
