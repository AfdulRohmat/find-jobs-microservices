package com.find_jobs.auth_service.client;


import com.find_jobs.auth_service.config.FeignClientConfig;
import com.find_jobs.auth_service.dto.response.UserResponseDTO;
import com.find_jobs.auth_service.dto.request.RegisterRequestDTO;
import com.find_jobs.auth_service.dto.response.RegisterResponseDTO;
import com.find_jobs.auth_service.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", path = "/api/v1/user")
public interface UserServiceClient {

//    @PostMapping("/register")
//    Response<RegisterResponseDTO> registerUser(@RequestBody RegisterRequestDTO request);
//
//    @GetMapping("/get-user/{username}")
//    ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username);

}
