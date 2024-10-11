package com.find_jobs.company_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyProfileRequestDTO {
    private MultipartFile companyLogoFile;

    private MultipartFile bannerFile;

    @NotBlank(message = "companyName name cannot be empty!")
    @NotNull(message = "companyName name is required!")
    private String companyName;

    @NotBlank(message = "description name cannot be empty!")
    @NotNull(message = "description name is required!")
    private String description;

    @NotBlank(message = "organizationType name cannot be empty!")
    @NotNull(message = "organizationType name is required!")
    private String organizationType;

    @NotBlank(message = "industryType name cannot be empty!")
    @NotNull(message = "industryType name is required!")
    private String industryType;

    @NotBlank(message = "teamSize name cannot be empty!")
    @NotNull(message = "teamSize name is required!")
    private String teamSize;

    @NotNull(message = "yearsOfEstablishment name is required!")
    private Integer yearsOfEstablishment;

    @NotBlank(message = "companyWebsite name cannot be empty!")
    @NotNull(message = "companyWebsite name is required!")
    private String companyWebsite;

    @NotBlank(message = "companyVision name cannot be empty!")
    @NotNull(message = "companyVision name is required!")
    private String companyVision;

    @NotNull(message = "socialMediaLinks name is required!")
    private List<String> socialMediaLinks;

    @NotBlank(message = "address name cannot be empty!")
    @NotNull(message = "address name is required!")
    private String address;

    @NotBlank(message = "phoneContact name cannot be empty!")
    @NotNull(message = "phoneContact name is required!")
    private String phoneContact;

    @NotBlank(message = "companyEmail name cannot be empty!")
    @NotNull(message = "companyEmail name is required!")
    private String companyEmail;
}