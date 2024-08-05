package com.find_jobs.job_service.controller;

import com.find_jobs.job_service.dto.request.JobRequestDTO;
import com.find_jobs.job_service.service.JobService;
import com.find_jobs.job_service.utils.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping(value = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createCourse(@Valid @RequestBody JobRequestDTO jobRequestDTO) {
        return ResponseEntity.ok(jobService.createJob(jobRequestDTO));
    }

    @GetMapping
    public ResponseEntity<Object> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJob(@PathVariable Long id, @RequestBody JobRequestDTO jobRequestDTO) {
        return ResponseEntity.ok(jobService.updateJob(id, jobRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJob(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.deleteJob(id));
    }

    @GetMapping("/test")
    public ResponseEntity<Object> getTest() {
        return ResponseEntity.ok("Hello World");
    }

}
