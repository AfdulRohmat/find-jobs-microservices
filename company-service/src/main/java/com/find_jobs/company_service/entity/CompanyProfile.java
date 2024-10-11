package com.find_jobs.company_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "company_profiles")
public class CompanyProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyLogo;
    private String banner;
    private String companyName;
    private String description;
    private String organizationType;
    private String industryType;
    private String teamSize;
    private Integer yearsOfEstablishment;
    private String companyWebsite;
    private String companyVision;

    @ElementCollection
    private List<String> socialMediaLinks;

    private String address;
    private String phoneContact;
    private String companyEmail;

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