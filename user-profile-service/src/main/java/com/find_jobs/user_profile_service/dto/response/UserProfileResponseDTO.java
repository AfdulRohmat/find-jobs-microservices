package com.find_jobs.user_profile_service.dto.response;

import com.find_jobs.user_profile_service.dto.request.AddressDTO;
import com.find_jobs.user_profile_service.dto.request.CareerHistoryDTO;
import com.find_jobs.user_profile_service.dto.request.EducationDTO;
import com.find_jobs.user_profile_service.entity.Address;
import com.find_jobs.user_profile_service.entity.CareerHistory;
import com.find_jobs.user_profile_service.entity.Education;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class UserProfileResponseDTO {
    private Long id;
    private Long userId;
    private String personalSummary;
    private Address address;
    private List<CareerHistory> careerHistory;
    private List<Education> education;
    private String cvUrl;
}
