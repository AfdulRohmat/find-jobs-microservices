package com.find_jobs.company_service.controller;

import com.find_jobs.company_service.dto.request.CompanyProfileRequestDTO;
import com.find_jobs.company_service.service.CompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company-profiles")
public class CompanyController {

    @Autowired
    CompanyProfileService companyProfileService;

    @GetMapping(value = "/test",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<Object> getTest() {
        return ResponseEntity.ok("Success Test");
    }

    @PostMapping(value = "/profile",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<Object> createCompanyProfile(@RequestBody CompanyProfileRequestDTO companyProfileRequestDTO) {
        return ResponseEntity.ok(companyProfileService.createCompanyProfile(companyProfileRequestDTO));
    }

    @GetMapping(value = "/employer/profile",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<Object> getCompanyProfileByEmployer() {
        return ResponseEntity.ok(companyProfileService.getCompanyProfile());
    }

    @GetMapping(value = "/profile",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> getCompanyProfile(@RequestParam(name = "companyId", defaultValue = "") Long companyId) {
        return ResponseEntity.ok(companyProfileService.getCompanyProfileById(companyId));
    }

}
