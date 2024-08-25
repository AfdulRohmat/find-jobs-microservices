package com.find_jobs.job_service.client;

import com.find_jobs.job_service.config.FeignClientConfig;
import com.find_jobs.job_service.entity.CompanyProfile;
import com.find_jobs.job_service.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "COMPANY-SERVICE", path = "/api/v1/company-profiles", configuration = FeignClientConfig.class)
public interface CompanyServiceClient {

    @GetMapping("/profile")
    Response<CompanyProfile> getCompanyById(@RequestParam(name = "companyId", defaultValue = "") Long companyId);
}