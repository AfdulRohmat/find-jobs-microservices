package com.find_jobs.notification_service.entity;


import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}
