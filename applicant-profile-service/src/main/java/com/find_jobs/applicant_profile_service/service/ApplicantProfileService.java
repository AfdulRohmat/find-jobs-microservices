package com.find_jobs.applicant_profile_service.service;

import com.find_jobs.applicant_profile_service.client.AuthServiceClient;
import com.find_jobs.applicant_profile_service.client.StorageServiceClient;
import com.find_jobs.applicant_profile_service.constant.Constant;
import com.find_jobs.applicant_profile_service.dto.request.*;
import com.find_jobs.applicant_profile_service.dto.response.ApplicantProfileResponseDTO;
import com.find_jobs.applicant_profile_service.dto.response.CloudinaryUploadResponseDTO;
import com.find_jobs.applicant_profile_service.entity.*;
import com.find_jobs.applicant_profile_service.exception.NotFoundException;
import com.find_jobs.applicant_profile_service.repository.*;
import com.find_jobs.applicant_profile_service.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantProfileService {

    @Autowired
    private ApplicantProfileRepository applicantProfileRepository;

    @Autowired
    private ApplicantAddressRepository applicantAddressRepository;

    @Autowired
    private CareerHistoryRepository careerHistoryRepository;

    @Autowired
    private EducationHistoryRepository educationHistoryRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private StorageServiceClient storageServiceClient;

    @Transactional
    public Response<Object> getApplicantProfile() {
        Response<User> userCurrentlyLogin = authServiceClient.getUserLogin();

        ApplicantProfile applicantProfile = applicantProfileRepository.findByUserId(userCurrentlyLogin.getData().getId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        ApplicantProfileResponseDTO responseDTO = ApplicantProfileResponseDTO.builder()
                .id(applicantProfile.getId())
                .userId(userCurrentlyLogin.getData().getId())
                .personalSummary(applicantProfile.getPersonalSummary())
                .cvUrl(applicantProfile.getCvUrl())
                .photoProfileUrl(applicantProfile.getPhotoProfileUrl())
                .address(mapToAddress(applicantProfile))
                .careerHistories(mapToCareerHistory(applicantProfile))
                .educationHistories(mapToEducationHistory(applicantProfile))
                .skills(mapToSkill(applicantProfile))
                .build();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(responseDTO)
                .build();
    }

    @Transactional
    public Response<Object> addApplicantProfile(ApplicantProfileRequestDTO request) {
        Response<User> userCurrentlyLogin = authServiceClient.getUserLogin();

        ApplicantProfile applicantProfile = new ApplicantProfile();

        applicantProfile.setUserId(userCurrentlyLogin.getData().getId());
        applicantProfile.setFullName(userCurrentlyLogin.getData().getUsername());
        applicantProfile.setPersonalSummary(request.getPersonalSummary());

        // upload File to Cloudinary
        Response<CloudinaryUploadResponseDTO> uploadCvToStorage = storageServiceClient.uploadFile(request.getCvFile(), "applicant-profile");
        Response<CloudinaryUploadResponseDTO> uploadPhotoProfileToStorage = storageServiceClient.uploadFile(request.getPhotoProfileFile(), "applicant-profile");

        applicantProfile.setCvUrl(uploadCvToStorage.getData().getSecure_url());
        applicantProfile.setCvPublicId(uploadCvToStorage.getData().getPublic_id());

        applicantProfile.setPhotoProfileUrl(uploadPhotoProfileToStorage.getData().getSecure_url());
        applicantProfile.setPhotoProfilePublicId(uploadPhotoProfileToStorage.getData().getPublic_id());

        applicantProfileRepository.save(applicantProfile);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(null)
                .build();
    }

    @Transactional
    public Response<Object> addApplicantAddress(AddressRequestDTO addressRequestDTO) {
        ApplicantProfile applicantProfile = applicantProfileRepository.findByUserId(addressRequestDTO.getApplicantProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));


        ApplicantAddress address = new ApplicantAddress();

        address.setApplicantProfileId(applicantProfile);
        address.setStreet(addressRequestDTO.getStreet());
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setPostalCode(addressRequestDTO.getPostalCode());
        address.setCountry(addressRequestDTO.getCountry());

        applicantAddressRepository.save(address);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(null)
                .build();
    }

    @Transactional
    public Response<Object> addCareerHistory(CareerHistoryRequestDTO careerHistoryRequestDTO) {
        ApplicantProfile applicantProfile = applicantProfileRepository.findById(careerHistoryRequestDTO.getApplicantProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));


        CareerHistory careerHistory = new CareerHistory();

        careerHistory.setApplicantProfileId(applicantProfile);
        careerHistory.setPosition(careerHistoryRequestDTO.getPosition());
        careerHistory.setCompanyName(careerHistoryRequestDTO.getCompanyName());
        careerHistory.setStartDate(careerHistoryRequestDTO.getStartDate());
        careerHistory.setEndDate(careerHistoryRequestDTO.getEndDate());
        careerHistory.setIsCurrentPosition(careerHistoryRequestDTO.getIsCurrentPosition());
        careerHistory.setDescription(careerHistoryRequestDTO.getDescription());

        careerHistoryRepository.save(careerHistory);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(null)
                .build();
    }

    @Transactional
    public Response<Object> addEducationHistory(EducationRequestDTO educationRequestDTO) {
        ApplicantProfile applicantProfile = applicantProfileRepository.findById(educationRequestDTO.getApplicantProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));


        EducationHistory education = new EducationHistory();

        education.setApplicantProfileId(applicantProfile);
        education.setInstitutionName(educationRequestDTO.getInstitutionName());
        education.setDegree(educationRequestDTO.getDegree());
        education.setFieldOfStudy(educationRequestDTO.getFieldOfStudy());
        education.setStartDate(educationRequestDTO.getStartDate());
        education.setEndDate(educationRequestDTO.getEndDate());
        education.setGrade(educationRequestDTO.getGrade());
        education.setAchievements(educationRequestDTO.getAchievements());

        educationHistoryRepository.save(education);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(null)
                .build();
    }

    @Transactional
    public Response<Object> addSkill(SkillRequestDTO skillRequestDTO) {
        ApplicantProfile applicantProfile = applicantProfileRepository.findById(skillRequestDTO.getApplicantProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        Skill skill = new Skill();

        skill.setApplicantProfileId(applicantProfile);
        skill.setSkillName(skill.getSkillName());
        skill.setProficiencyLevel(skill.getProficiencyLevel());

        skillRepository.save(skill);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(null)
                .build();
    }

    private static ApplicantAddress mapToAddress(ApplicantProfile applicantProfile) {

        ApplicantAddress address = applicantProfile.getAddress();

        ApplicantAddress addressDTO = new ApplicantAddress();

        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setCountry(address.getCountry());

        return addressDTO;
    }

    private static List<CareerHistory> mapToCareerHistory(ApplicantProfile applicantProfile) {
        return applicantProfile.getCareerHistories().stream().map(career -> {
            CareerHistory dtoCareer = new CareerHistory();

            dtoCareer.setId(career.getId());
            dtoCareer.setPosition(career.getPosition());
            dtoCareer.setCompanyName(career.getCompanyName());
            dtoCareer.setStartDate(career.getStartDate());
            dtoCareer.setEndDate(career.getEndDate());
            dtoCareer.setDescription(career.getDescription());

            return dtoCareer;

        }).collect(Collectors.toList());
    }

    private static List<EducationHistory> mapToEducationHistory(ApplicantProfile applicantProfile) {
        return applicantProfile.getEducationHistories().stream().map(educationHistory -> {
            EducationHistory dtoEducation = new EducationHistory();

            dtoEducation.setId(educationHistory.getId());
            dtoEducation.setInstitutionName(educationHistory.getInstitutionName());
            dtoEducation.setDegree(educationHistory.getDegree());
            dtoEducation.setFieldOfStudy(educationHistory.getFieldOfStudy());
            dtoEducation.setStartDate(educationHistory.getStartDate());
            dtoEducation.setEndDate(educationHistory.getEndDate());
            dtoEducation.setGrade(educationHistory.getGrade());
            dtoEducation.setAchievements(educationHistory.getAchievements());

            return dtoEducation;

        }).collect(Collectors.toList());
    }

    private static List<Skill> mapToSkill(ApplicantProfile applicantProfile) {
        return applicantProfile.getSkills().stream().map(skill -> {
            Skill dtoSkill = new Skill();

            dtoSkill.setId(skill.getId());
            dtoSkill.setSkillName(skill.getSkillName());
            dtoSkill.setProficiencyLevel(skill.getProficiencyLevel());

            return dtoSkill;

        }).collect(Collectors.toList());
    }
}
