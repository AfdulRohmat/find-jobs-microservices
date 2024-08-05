package com.find_jobs.user_profile_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "career_histories")
public class CareerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userProfileId;

    private String jobTitle;
    private String companyName;
    private Date startDate;
    private Date endDate;
    private String responsibilities;
    private String achievements;
}
