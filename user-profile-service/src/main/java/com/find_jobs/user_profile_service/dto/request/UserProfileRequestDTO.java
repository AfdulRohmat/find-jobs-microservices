package com.find_jobs.user_profile_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileRequestDTO {
    private Long id;
    private Long userId;
    private String personalSummary;
    private String cvUrl;
}
