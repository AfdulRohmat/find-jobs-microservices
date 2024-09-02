package com.find_jobs.application_service.entity;


import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}
