package com.find_jobs.user_profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
