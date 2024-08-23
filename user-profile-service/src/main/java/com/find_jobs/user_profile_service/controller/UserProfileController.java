package com.find_jobs.user_profile_service.controller;

import com.find_jobs.user_profile_service.dto.request.*;
import com.find_jobs.user_profile_service.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-profiles")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping(value = "/test",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> getTest() {
        return ResponseEntity.ok("Success Test");
    }

    @PostMapping(value = "/profile",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> addUserProfile(@RequestBody UserProfileRequestDTO userProfileRequestDTO) {
        return ResponseEntity.ok(userProfileService.addUserProfile(userProfileRequestDTO));
    }

    @PutMapping(value = "/profile/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileRequestDTO userProfileRequestDTO) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(id, userProfileRequestDTO));
    }

    @PostMapping(value = "/address",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> addAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(userProfileService.addAddress(addressDTO));
    }

    @PutMapping(value = "/address/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(userProfileService.updateAddress(id, addressDTO));
    }

    @PostMapping(value = "/career-history",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> addCareerHistory(@RequestBody CareerHistoryDTO careerHistoryDTO) {
        return ResponseEntity.ok(userProfileService.addCareerHistory(careerHistoryDTO));
    }

    @PutMapping(value = "/career-history/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> updateCareerHistory(@PathVariable Long id, @RequestBody CareerHistoryDTO careerHistoryDTO) {
        return ResponseEntity.ok(userProfileService.updateCareerHistory(id, careerHistoryDTO));
    }

    @PostMapping(value = "/education",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> addEducation(@RequestBody EducationDTO educationDTO) {
        return ResponseEntity.ok(userProfileService.addEducation(educationDTO));
    }

    @PutMapping(value = "/education/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> updateEducation(@PathVariable Long id, @RequestBody EducationDTO educationDTO) {
        return ResponseEntity.ok(userProfileService.updateEducation(id, educationDTO));
    }

    @GetMapping(value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    public ResponseEntity<Object> getUserProfile(@RequestParam(name = "userId", defaultValue = "") Long userId) {
        return ResponseEntity.ok(userProfileService.getUserProfile(userId));
    }

}
