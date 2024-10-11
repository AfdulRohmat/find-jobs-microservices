package com.find_jobs.job_service.dto.response;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

}
