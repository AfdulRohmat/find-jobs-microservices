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
public class JobRequestDTO {
    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String title;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String description;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String location;

    @NotNull(message = "Field cannot be empty!")
    private Long companyId;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String employmentType;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String salary;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String experienceLevel;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String yearsOfExperience;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String qualifications;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String benefits;

    @NotNull(message = "Field cannot be empty!")
    private LocalDate postedDate;

    @NotNull(message = "Field cannot be empty!")
    private LocalDate applicationDeadline;

    private List<String> skills;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String industry;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String jobFunction;

    @NotBlank(message = "Field cannot be empty!")
    @NotNull(message = "Field cannot be empty!")
    private String educationLevel;
}
