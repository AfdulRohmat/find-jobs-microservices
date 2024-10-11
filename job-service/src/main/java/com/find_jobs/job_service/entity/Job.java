package com.find_jobs.job_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ElementCollection
    private List<String> skills;

    private LocalDate expirationDate;
    private String jobLevel;
    private String locationCountry;
    private String locationCity;
    private Boolean enableRemote;

    @ElementCollection
    private List<String> jobBenefits;

    private String jobDescription;
    private Long companyId;

    private Long createdByUserId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

}
