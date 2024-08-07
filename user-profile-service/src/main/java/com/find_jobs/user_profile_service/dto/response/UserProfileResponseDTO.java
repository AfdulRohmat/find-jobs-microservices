package com.find_jobs.user_profile_service.dto.response;

import com.find_jobs.user_profile_service.entity.UserProfileAddress;
import com.find_jobs.user_profile_service.entity.CareerHistory;
import com.find_jobs.user_profile_service.entity.Education;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserProfileResponseDTO {
    private Long id;
    private Long userId;
    private String personalSummary;
    private UserProfileAddress userProfileAddress;
    private List<CareerHistory> careerHistory;
    private List<Education> education;
    private String cvUrl;
}
