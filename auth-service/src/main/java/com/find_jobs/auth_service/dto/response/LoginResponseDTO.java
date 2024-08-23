package com.find_jobs.auth_service.dto.response;

import com.find_jobs.auth_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private Long userId;
    private String username;
    private String email;
    private String accessToken;
    private List<String> role;

}