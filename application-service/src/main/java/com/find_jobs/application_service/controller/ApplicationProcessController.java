package com.find_jobs.application_service.controller;

import com.find_jobs.application_service.dto.request.ApplicationRequestDTO;
import com.find_jobs.application_service.dto.response.ApplicationResponseDTO;
import com.find_jobs.application_service.entity.enums.ApplicationStatus;
import com.find_jobs.application_service.service.ApplicationProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/application")
public class ApplicationProcessController {

    @Autowired
    ApplicationProcessService applicationProcessService;

    @GetMapping(value = "/test",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('APPLICANT')")
    public ResponseEntity<Object> getTest() {
        return ResponseEntity.ok("Success Test");
    }

    @PostMapping(value = "/apply-job",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<Object> applyForJob(@RequestBody ApplicationRequestDTO dto) {
        return ResponseEntity.ok(applicationProcessService.applyJob(dto));
    }

    @GetMapping(value = "/detail", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getApplicationDetails(@RequestParam(name = "application_id", defaultValue = "") Long id) {
        return ResponseEntity.ok(applicationProcessService.getApplicationDetails(id));
    }

    @PutMapping("/status")
    public ResponseEntity<Object> updateApplicationStatus(@RequestParam(name = "application_id") Long id,
                                                          @RequestParam(name = "status") ApplicationStatus status,
                                                          @RequestParam(name = "change_by_user_id") Long changedByUserId) {

        return ResponseEntity.ok(applicationProcessService.updateApplicationStatus(id, status, changedByUserId));
    }

    @GetMapping(value = "/for-applicant")
    public ResponseEntity<Object> getAllApplicationsForApplicant(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(name = "applicant_id", required = false) Long applicantId,
            @RequestParam(required = false) ApplicationStatus status
    ) {
        return ResponseEntity.ok(applicationProcessService.getAllApplicationsForApplicant(page, size, applicantId, status));
    }

    @GetMapping(value = "/for-company")
    public ResponseEntity<Object> getAllApplicationsForCompany(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(name = "company_id", required = false) Long companyId,
            @RequestParam(required = false) ApplicationStatus status
    ) {
        return ResponseEntity.ok(applicationProcessService.getAllApplicationsForCompany(page, size, companyId, status));
    }


}
