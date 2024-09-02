package com.find_jobs.application_service.service;

import com.find_jobs.application_service.constant.Constant;
import com.find_jobs.application_service.dto.request.ApplicationRequestDTO;
import com.find_jobs.application_service.dto.response.ApplicationResponseDTO;
import com.find_jobs.application_service.entity.Application;
import com.find_jobs.application_service.entity.enums.ApplicationStatus;
import com.find_jobs.application_service.exception.NotFoundException;
import com.find_jobs.application_service.repository.ApplicationProcessRepository;
import com.find_jobs.application_service.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationProcessService {

    @Autowired
    ApplicationProcessRepository applicationProcessRepository;

    @Autowired
    ApplicationStatusHistoryService applicationStatusHistoryService;

    @Transactional
    public Response<Object> applyJob(ApplicationRequestDTO applicationRequestDTO) {

        Application application = Application.builder()
                .jobId(applicationRequestDTO.getJobId())
                .applicantId(applicationRequestDTO.getApplicantId())
                .companyId(applicationRequestDTO.getCompanyId())
                .status(ApplicationStatus.APPLIED)
                .resumeUrl(applicationRequestDTO.getResumeUrl())
                .coverLetter(applicationRequestDTO.getCoverLetter())
                .build();

        Application savedApplication = applicationProcessRepository.save(application);

        // add data application to log
        applicationStatusHistoryService.logApplicationStatusChange(savedApplication.getId(),
                savedApplication.getStatus(), applicationRequestDTO.getApplicantId());

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(savedApplication)
                .build();
    }


    // Get ApplicationDetail ( later will update with data company and applicant )
    @Transactional
    public Response<Object> getApplicationDetails(Long applicationId) {

        Application application = applicationProcessRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(mapToApplicationResponseDTO(application))
                .build();
    }

    // Update status of application (only user with role employer can access this api)
    @Transactional
    public Response<Object> updateApplicationStatus(Long applicationId, ApplicationStatus status, Long changedBy) {

        Application application = applicationProcessRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        application.setStatus(status);

        Application updatedApplication = applicationProcessRepository.save(application);

        applicationStatusHistoryService.logApplicationStatusChange(applicationId, status, changedBy);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(updatedApplication)
                .build();
    }

    // get application data base on applicantId
    @Transactional
    public Response<Object> getAllApplicationsForApplicant(int page, int size, Long applicantId, ApplicationStatus status) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Application> applications = applicationProcessRepository.getApplicationsByApplicantId(applicantId, status, pageable);

        List<ApplicationResponseDTO> applicationResponseDTOList = applications.stream()
                .map(this::mapToApplicationResponseDTO).toList();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(applicationResponseDTOList)
                .build();
    }


    // get application data base on companyId
    @Transactional
    public Response<Object> getAllApplicationsForCompany(int page, int size, Long companyId, ApplicationStatus status) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Application> applications = applicationProcessRepository.getApplicationsByCompanyId(companyId, status, pageable);

        List<ApplicationResponseDTO> applicationResponseDTOList = applications.stream()
                .map(this::mapToApplicationResponseDTO).toList();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(applicationResponseDTOList)
                .build();
    }


    private ApplicationResponseDTO mapToApplicationResponseDTO(Application application) {
        return ApplicationResponseDTO.builder()
                .id(application.getId())
                .jobId(application.getJobId())
                .applicantId(application.getApplicantId())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .updatedAt(application.getUpdatedAt())
                .resumeUrl(application.getResumeUrl())
                .coverLetter(application.getCoverLetter())
                .build();
    }


}
