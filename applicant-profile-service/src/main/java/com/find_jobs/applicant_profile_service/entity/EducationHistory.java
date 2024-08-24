package com.find_jobs.applicant_profile_service.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "education_histories")
public class EducationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_profile_id", nullable = false)
    private ApplicantProfile applicantProfileId;

    private String institutionName;
    private String degree;
    private String fieldOfStudy;
    private String startDate;
    private String endDate;
    private String grade;
    private String achievements;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}