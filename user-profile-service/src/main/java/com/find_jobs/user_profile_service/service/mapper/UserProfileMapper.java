package com.find_jobs.user_profile_service.service.mapper;

import com.find_jobs.user_profile_service.dto.request.CareerHistoryDTO;
import com.find_jobs.user_profile_service.dto.request.EducationDTO;
import com.find_jobs.user_profile_service.dto.response.UserProfileResponseDTO;
import com.find_jobs.user_profile_service.entity.UserProfileAddress;
import com.find_jobs.user_profile_service.entity.CareerHistory;
import com.find_jobs.user_profile_service.entity.Education;
import com.find_jobs.user_profile_service.entity.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserProfileMapper {
    public Education getEducation(EducationDTO educationDTO, UserProfile savedUserProfile) {
        Education education = new Education();

        education.setUserProfileId(savedUserProfile.getId());
        education.setInstitutionName(educationDTO.getInstitutionName());
        education.setDegree(educationDTO.getDegree());
        education.setFieldOfStudy(educationDTO.getFieldOfStudy());
        education.setStartDate(educationDTO.getStartDate());
        education.setEndDate(educationDTO.getEndDate());
        education.setGrade(educationDTO.getGrade());
        education.setAchievements(educationDTO.getAchievements());

        return education;
    }

    public CareerHistory getCareerHistory(CareerHistoryDTO careerHistoryDTO, UserProfile savedUserProfile) {
        CareerHistory careerHistory = new CareerHistory();

        careerHistory.setUserProfileId(savedUserProfile.getId());
        careerHistory.setJobTitle(careerHistoryDTO.getJobTitle());
        careerHistory.setCompanyName(careerHistoryDTO.getCompanyName());
        careerHistory.setStartDate(careerHistoryDTO.getStartDate());
        careerHistory.setEndDate(careerHistoryDTO.getEndDate());
        careerHistory.setResponsibilities(careerHistoryDTO.getResponsibilities());
        careerHistory.setAchievements(careerHistoryDTO.getAchievements());

        return careerHistory;
    }

    public UserProfileResponseDTO mapToUserProfileResponseDTO(UserProfile userProfile, UserProfileAddress userProfileAddress,
                                                              List<CareerHistory> careerHistories, List<Education> educations) {
        return UserProfileResponseDTO.builder()
                .id(userProfile.getId())
                .userId(userProfile.getUserId())
                .personalSummary(userProfile.getPersonalSummary())
                .userProfileAddress(mapToAddressDTO(userProfileAddress))
                .careerHistory(careerHistories.stream().map(this::mapToCareerHistoryDTO).collect(Collectors.toList()))
                .education(educations.stream().map(this::mapToEducationDTO).collect(Collectors.toList()))
                .cvUrl(userProfile.getCvUrl())
                .build();
    }


    private UserProfileAddress mapToAddressDTO(UserProfileAddress userProfileAddress) {
        return UserProfileAddress.builder()
                .id(userProfileAddress.getId())
                .userProfileId(userProfileAddress.getUserProfileId())
                .street(userProfileAddress.getStreet())
                .city(userProfileAddress.getCity())
                .state(userProfileAddress.getState())
                .postalCode(userProfileAddress.getPostalCode())
                .country(userProfileAddress.getCountry())
                .build();
    }

    private CareerHistory mapToCareerHistoryDTO(CareerHistory careerHistory) {
        return CareerHistory.builder()
                .id(careerHistory.getId())
                .userProfileId(careerHistory.getUserProfileId())
                .jobTitle(careerHistory.getJobTitle())
                .companyName(careerHistory.getCompanyName())
                .startDate(careerHistory.getStartDate())
                .endDate(careerHistory.getEndDate())
                .responsibilities(careerHistory.getResponsibilities())
                .achievements(careerHistory.getAchievements())
                .build();
    }

    private Education mapToEducationDTO(Education education) {
        return Education.builder()
                .id(education.getId())
                .userProfileId(education.getUserProfileId())
                .institutionName(education.getInstitutionName())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .grade(education.getGrade())
                .achievements(education.getAchievements())
                .build();
    }
}
