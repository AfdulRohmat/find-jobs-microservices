package com.find_jobs.job_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String companyName;
    private String salary;
    private String employmentType;
    private String experienceLevel;
    private String yearsOfExperience;
    private LocalDate postedDate;
    private LocalDate expiryDate;

    @ElementCollection
    private List<String> skills;

    private String industry;
    private String jobFunction;
    private String educationLevel;

}
