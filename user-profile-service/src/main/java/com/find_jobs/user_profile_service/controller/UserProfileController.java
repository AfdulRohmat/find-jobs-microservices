package com.find_jobs.user_profile_service.controller;

import com.find_jobs.user_profile_service.dto.request.UserProfileRequestDTO;
import com.find_jobs.user_profile_service.entity.UserProfile;
import com.find_jobs.user_profile_service.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-profiles")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping(value = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> createUserProfile(@RequestBody UserProfileRequestDTO userProfileRequestDTO) {
        return ResponseEntity.ok(userProfileService.createUserProfile(userProfileRequestDTO));
    }

}
