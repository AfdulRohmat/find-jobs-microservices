package com.find_jobs.user_profile_service.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adress")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userProfileId;

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}