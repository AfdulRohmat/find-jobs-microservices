package com.find_jobs.auth_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponseDTO {
    private String username;
    private String email;
}
