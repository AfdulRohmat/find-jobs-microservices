package com.find_jobs.job_service.dto.request;

import jakarta.persistence.ElementCollection;
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
public class JobRequestDTO {

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String jobTitle;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String tags;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String jobRole;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private Double minSalary;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private Double maxSalary;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String salaryType;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String education;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String experience;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String jobType;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private Integer vacancies;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private List<String> skills;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private LocalDate expirationDate;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String jobLevel;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String locationCountry;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String locationCity;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private Boolean enableRemote;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private List<String> jobBenefits;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String jobDescription;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private Long companyId;
}
