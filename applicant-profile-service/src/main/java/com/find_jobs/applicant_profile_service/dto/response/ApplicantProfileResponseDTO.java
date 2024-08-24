package com.find_jobs.applicant_profile_service.dto.response;

import com.find_jobs.applicant_profile_service.entity.ApplicantAddress;
import com.find_jobs.applicant_profile_service.entity.EducationHistory;
import com.find_jobs.applicant_profile_service.entity.CareerHistory;
import com.find_jobs.applicant_profile_service.entity.Skill;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApplicantProfileResponseDTO {
    private Long id;
    private Long userId;
    private String personalSummary;
    private String cvUrl;
    private ApplicantAddress address;
    private List<CareerHistory> careerHistories;
    private List<EducationHistory> educationHistories;
    private List<Skill> skills;
}
