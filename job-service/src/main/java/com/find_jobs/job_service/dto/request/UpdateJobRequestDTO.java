package com.find_jobs.job_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdateJobRequestDTO {

    private String jobTitle;

    private String tags;

    private String jobRole;

    private Double minSalary;

    private Double maxSalary;

    private String salaryType;

    private String education;

    private String experience;

    private String jobType;

    private Integer vacancies;

    private List<String> skills;

    private LocalDate expirationDate;

    private String jobLevel;

    private String locationCountry;

    private String locationCity;

    private Boolean enableRemote;

    private List<String> jobBenefits;

    private String jobDescription;

    private Long companyId;
}
