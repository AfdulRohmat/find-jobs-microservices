package com.find_jobs.applicant_profile_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationRequestDTO {
    private Long applicantProfileId;

    private String institutionName;
    private String degree;
    private String fieldOfStudy;
    private String startDate;
    private String endDate;
    private String grade;
    private String achievements;

}
