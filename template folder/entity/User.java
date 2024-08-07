package com.find_jobs.company_service.entity;


import lombok.Data;

@Data
public class User {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String role;

    private String gender;

}
