package com.find_jobs.applicant_profile_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applicant_profiles")
public class ApplicantProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String fullName;
    private String personalSummary;

    private String photoProfileUrl;
    private String photoProfilePublicId;

    private String cvUrl;
    private String cvPublicId;

    @OneToOne(mappedBy = "applicantProfileId", cascade = CascadeType.ALL)
    private ApplicantAddress address;

    @OneToMany(mappedBy = "applicantProfileId", cascade = CascadeType.ALL)
    private List<CareerHistory> careerHistories;

    @OneToMany(mappedBy = "applicantProfileId", cascade = CascadeType.ALL)
    private List<EducationHistory> educationHistories;

    @OneToMany(mappedBy = "applicantProfileId", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
