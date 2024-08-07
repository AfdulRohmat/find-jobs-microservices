package com.find_jobs.company_service.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyProfileRequestDTO {
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
}