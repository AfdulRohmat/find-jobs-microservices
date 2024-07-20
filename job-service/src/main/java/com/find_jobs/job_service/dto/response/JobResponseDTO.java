package com.find_jobs.job_service.dto.response;

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
    private String title;
    private String description;
    private String location;
    private String company;
    private String employmentType;
    private String salary;
    private String experienceLevel;
    private String yearsOfExperience;
    private String qualifications;
    private String benefits;
    private LocalDate postedDate;
    private LocalDate applicationDeadline;
    private List<String> skills;
    private String industry;
    private String jobFunction;
    private String educationLevel;
}
