package com.find_jobs.company_service.service;

import com.find_jobs.company_service.client.AuthServiceClient;
import com.find_jobs.company_service.client.StorageServiceClient;
import com.find_jobs.company_service.constant.Constant;
import com.find_jobs.company_service.dto.request.CompanyProfileRequestDTO;
import com.find_jobs.company_service.dto.request.UpdateCompanyProfileRequestDTO;
import com.find_jobs.company_service.dto.response.CloudinaryUploadResponseDTO;
import com.find_jobs.company_service.entity.CompanyProfile;
import com.find_jobs.company_service.dto.response.UserResponseDTO;
import com.find_jobs.company_service.exception.NotFoundException;
import com.find_jobs.company_service.repository.CompanyProfileRepository;
import com.find_jobs.company_service.service.mapper.CompanyProfileMapper;
import com.find_jobs.company_service.util.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyProfileService {

    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @Autowired
    private CompanyProfileMapper companyProfileMapper;

    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private StorageServiceClient storageServiceClient;

    @Transactional
    public Response<Object> createCompanyProfile(CompanyProfileRequestDTO dto) {
        Response<UserResponseDTO> userCurrentlyLogin = authServiceClient.getUserLogin();

        Response<CloudinaryUploadResponseDTO> uploadCompanyLogoToStorage = storageServiceClient.uploadFile(dto.getCompanyLogoFile(), "company-logo");
        Response<CloudinaryUploadResponseDTO> uploadCompanyBannerToStorage = storageServiceClient.uploadFile(dto.getBannerFile(), "company-banner");

        CompanyProfile companyProfile = CompanyProfile.builder()
                .companyLogo(uploadCompanyLogoToStorage.getData().getSecure_url())
                .banner(uploadCompanyBannerToStorage.getData().getSecure_url())
                .companyName(dto.getCompanyName())
                .description(dto.getDescription())
                .organizationType(dto.getOrganizationType())
                .industryType(dto.getIndustryType())
                .teamSize(dto.getTeamSize())
                .yearsOfEstablishment(dto.getYearsOfEstablishment())
                .companyWebsite(dto.getCompanyWebsite())
                .companyVision(dto.getCompanyVision())
                .socialMediaLinks(dto.getSocialMediaLinks())
                .address(dto.getAddress())
                .phoneContact(dto.getPhoneContact())
                .companyEmail(dto.getCompanyEmail())
                .createdByUserId(userCurrentlyLogin.getData().getId())
                .build();

        CompanyProfile savedCompanyProfile = companyProfileRepository.save(companyProfile);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(savedCompanyProfile)
                .build();
    }

    @Transactional
    public Response<Object> updateCompanyProfile(UpdateCompanyProfileRequestDTO dto) {
        CompanyProfile companyProfile = companyProfileRepository.findById(dto.getCompanyId()).orElseThrow(() -> new NotFoundException("Data not found"));

        Response<CloudinaryUploadResponseDTO> uploadCompanyLogoToStorage = storageServiceClient.uploadFile(dto.getCompanyLogoFile(), "company-logo");
        Response<CloudinaryUploadResponseDTO> uploadCompanyBannerToStorage = storageServiceClient.uploadFile(dto.getBannerFile(), "company-banner");

        companyProfile.setCompanyLogo(uploadCompanyLogoToStorage.getData().getSecure_url());
        companyProfile.setBanner(uploadCompanyBannerToStorage.getData().getSecure_url());
        companyProfile.setCompanyName(dto.getCompanyName());
        companyProfile.setDescription(dto.getDescription());
        companyProfile.setOrganizationType(dto.getOrganizationType());
        companyProfile.setIndustryType(dto.getIndustryType());
        companyProfile.setTeamSize(dto.getTeamSize());
        companyProfile.setYearsOfEstablishment(dto.getYearsOfEstablishment());
        companyProfile.setCompanyWebsite(dto.getCompanyWebsite());
        companyProfile.setCompanyVision(dto.getCompanyVision());
        companyProfile.setSocialMediaLinks(dto.getSocialMediaLinks());
        companyProfile.setAddress(dto.getAddress());
        companyProfile.setPhoneContact(dto.getPhoneContact());
        companyProfile.setCompanyEmail(dto.getCompanyEmail());

        CompanyProfile updatedProfile = companyProfileRepository.save(companyProfile);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(updatedProfile)
                .build();
    }


    @Transactional
    public Response<Object> getCompanyProfile() {

        Response<UserResponseDTO> userCurrentlyLogin = authServiceClient.getUserLogin();

        CompanyProfile companyProfile = companyProfileRepository.findByCreatedByUserId(userCurrentlyLogin.getData().getId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(companyProfile)
                .build();
    }

    @Transactional
    public Response<Object> getCompanyProfileById(Long companyId) {

        CompanyProfile companyProfile = companyProfileRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(companyProfile)
                .build();
    }

}
