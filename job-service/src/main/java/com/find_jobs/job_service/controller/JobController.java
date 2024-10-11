package com.find_jobs.job_service.controller;

import com.find_jobs.job_service.dto.request.JobRequestDTO;
import com.find_jobs.job_service.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @PreAuthorize("hasRole('EMPLOYER')")
    @PostMapping(value = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createJob(@Valid @RequestBody JobRequestDTO jobRequestDTO) {
        return ResponseEntity.ok(jobService.createJob(jobRequestDTO));
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) String locationCountry,
            @RequestParam(required = false) String locationCity,
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) String jobType
    ) {
        return ResponseEntity.ok(jobService.getAllJobs(page, size, jobTitle, locationCountry, locationCity, companyId, jobType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Object> getJobByCompanyId(@PathVariable Long companyId) {
        return ResponseEntity.ok(jobService.getJobPostedByCompany(companyId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJob(@PathVariable Long id, @RequestBody JobRequestDTO jobRequestDTO) {
        return ResponseEntity.ok(jobService.updateJob(id, jobRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJob(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.deleteJob(id));
    }

    @GetMapping(value = "/test",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT') or hasRole('EMPLOYER')")
    public ResponseEntity<Object> getTest() {
        return ResponseEntity.ok("Success Test");
    }

}
