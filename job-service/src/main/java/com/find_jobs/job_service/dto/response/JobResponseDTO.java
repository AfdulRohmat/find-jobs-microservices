package com.find_jobs.job_service.dto.response;

import com.find_jobs.job_service.entity.CompanyProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String salary;
    private String employmentType;
    private String experienceLevel;
    private String yearsOfExperience;
    private LocalDate postedDate;
    private LocalDate expiryDate;
    private List<String> skills;
    private String industry;
    private String jobFunction;
    private String educationLevel;

    private Long companyId;
    private CompanyProfile company;
}
