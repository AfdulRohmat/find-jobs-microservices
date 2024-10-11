package com.find_jobs.job_service.dto.response;


import lombok.*;

@Data
public class CompanyProfileResponseDTO {

    private Long id;
    private String name;

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    private String website;
    private String description;
    private String industry;
    private String size;
    private String profileImageUrl;

    private Long createdByUserId;
}