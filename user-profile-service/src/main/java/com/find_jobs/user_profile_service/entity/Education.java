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
@Table(name = "educations")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userProfileId;

    private String institutionName;
    private String degree;
    private String fieldOfStudy;
    private Date startDate;
    private Date endDate;
    private String grade;
    private String achievements;
}
