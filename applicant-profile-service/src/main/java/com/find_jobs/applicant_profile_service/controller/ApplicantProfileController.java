package com.find_jobs.applicant_profile_service.controller;

import com.find_jobs.applicant_profile_service.dto.request.*;
import com.find_jobs.applicant_profile_service.service.ApplicantProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applicant-profiles")
public class ApplicantProfileController {
    @Autowired
    private ApplicantProfileService applicantProfileService;

    @GetMapping(value = "/test",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> getTest() {
        return ResponseEntity.ok("Success Test");
    }

    @PostMapping(value = "/profile",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> addUserProfile(@ModelAttribute @Valid ApplicantProfileRequestDTO applicantProfileRequestDTO) {
        return ResponseEntity.ok(applicantProfileService.addApplicantProfile(applicantProfileRequestDTO));
    }

//    @PutMapping(value = "/profile/{id}",
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    @PreAuthorize("hasRole('APPLICANT')")
//    public ResponseEntity<Object> updateUserProfile(@PathVariable Long id, @RequestBody ApplicantProfileRequestDTO applicantProfileRequestDTO) {
//        return ResponseEntity.ok(applicantProfileService.updateUserProfile(id, applicantProfileRequestDTO));
//    }

    @PostMapping(value = "/address",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> addAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        return ResponseEntity.ok(applicantProfileService.addApplicantAddress(addressRequestDTO));
    }

//    @PutMapping(value = "/address/{id}",
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    @PreAuthorize("hasRole('APPLICANT')")
//    public ResponseEntity<Object> updateAddress(@PathVariable Long id, @RequestBody AddressRequestDTO addressRequestDTO) {
//        return ResponseEntity.ok(applicantProfileService.updateAddress(id, addressRequestDTO));
//    }

    @PostMapping(value = "/career-history",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> addCareerHistory(@RequestBody CareerHistoryRequestDTO careerHistoryDTO) {
        return ResponseEntity.ok(applicantProfileService.addCareerHistory(careerHistoryDTO));
    }

//    @PutMapping(value = "/career-history/{id}",
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    @PreAuthorize("hasRole('APPLICANT')")
//    public ResponseEntity<Object> updateCareerHistory(@PathVariable Long id, @RequestBody CareerHistoryRequestDTO careerHistoryDTO) {
//        return ResponseEntity.ok(applicantProfileService.updateCareerHistory(id, careerHistoryDTO));
//    }

    @PostMapping(value = "/education-history",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> addEducation(@RequestBody EducationRequestDTO educationRequestDTO) {
        return ResponseEntity.ok(applicantProfileService.addEducationHistory(educationRequestDTO));
    }

//    @PutMapping(value = "/education/{id}",
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    @PreAuthorize("hasRole('APPLICANT')")
//    public ResponseEntity<Object> updateEducation(@PathVariable Long id, @RequestBody EducationRequestDTO educationRequestDTO) {
//        return ResponseEntity.ok(applicantProfileService.updateEducation(id, educationRequestDTO));
//    }

    @PostMapping(value = "/skill",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> addSkill(@RequestBody SkillRequestDTO skillRequestDTO) {
        return ResponseEntity.ok(applicantProfileService.addSkill(skillRequestDTO));
    }

    @GetMapping(value = "/profile",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    public ResponseEntity<Object> getUserProfile() {
        return ResponseEntity.ok(applicantProfileService.getApplicantProfile());
    }

}
