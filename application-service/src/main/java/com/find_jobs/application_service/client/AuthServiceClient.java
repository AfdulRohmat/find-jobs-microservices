package com.find_jobs.application_service.client;


import com.find_jobs.application_service.config.FeignClientConfig;
import com.find_jobs.application_service.entity.User;
import com.find_jobs.application_service.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AUTH-SERVICE", path = "/api/v1/auth", configuration = FeignClientConfig.class)
public interface AuthServiceClient {

    @GetMapping("/user")
    Response<User> getUserLogin();
}
