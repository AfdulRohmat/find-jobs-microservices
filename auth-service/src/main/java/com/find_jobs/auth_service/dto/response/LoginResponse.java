package com.find_jobs.auth_service.dto.response;

import com.find_jobs.auth_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private Long userId;
    private String username;
    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private Role role;

}