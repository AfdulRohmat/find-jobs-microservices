package com.find_jobs.application_process_service.client;


import com.find_jobs.application_process_service.config.FeignClientConfig;
import com.find_jobs.application_process_service.dto.response.CompanyResponseDTO;
import com.find_jobs.application_process_service.entity.User;
import com.find_jobs.application_process_service.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "COMPANY-SERVICE", path = "/api/v1/company-profiles", configuration = FeignClientConfig.class)
public interface CompanyServiceClient {

    @GetMapping("/profile")
    Response<CompanyResponseDTO> getCompanyProfile(
            @RequestParam(name = "companyId") Long companyId);
}
