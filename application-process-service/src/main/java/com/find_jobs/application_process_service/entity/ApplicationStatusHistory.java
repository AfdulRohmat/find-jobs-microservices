package com.find_jobs.application_process_service.entity;

import com.find_jobs.application_process_service.entity.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "application_status_histories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private Timestamp changedAt;

    private Long changedByUserId;

    @PrePersist
    protected void onCreate() {
        changedAt = new Timestamp(System.currentTimeMillis());
    }
}
