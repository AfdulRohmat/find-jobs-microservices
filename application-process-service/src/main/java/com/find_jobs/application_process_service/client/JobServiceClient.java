package com.find_jobs.application_process_service.client;

import com.find_jobs.application_process_service.config.FeignClientConfig;
import com.find_jobs.application_process_service.dto.response.CompanyResponseDTO;
import com.find_jobs.application_process_service.dto.response.JobResponseDTO;
import com.find_jobs.application_process_service.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "JOB-SERVICE", path = "/api/v1/jobs", configuration = FeignClientConfig.class)
public interface JobServiceClient {

    @GetMapping("/{id}")
    Response<JobResponseDTO> getJobById(@PathVariable Long id);
}
