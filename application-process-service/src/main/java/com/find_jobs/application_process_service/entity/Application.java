package com.find_jobs.application_process_service.entity;

import com.find_jobs.application_process_service.entity.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "applications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private Long applicantId;

    private Long companyId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(nullable = false, updatable = false)
    private Timestamp appliedAt;

    private Timestamp updatedAt;

    private String resumeUrl;

    @Column(length = 1000)
    private String coverLetter;

    @PrePersist
    protected void onCreate() {
        appliedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
