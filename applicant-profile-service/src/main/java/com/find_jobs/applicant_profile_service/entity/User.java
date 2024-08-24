package com.find_jobs.applicant_profile_service.entity;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

}
