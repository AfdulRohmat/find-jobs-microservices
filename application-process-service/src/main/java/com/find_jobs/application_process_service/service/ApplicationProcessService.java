package com.find_jobs.application_process_service.service;

import com.find_jobs.application_process_service.client.CompanyServiceClient;
import com.find_jobs.application_process_service.client.JobServiceClient;
import com.find_jobs.application_process_service.constant.Constant;
import com.find_jobs.application_process_service.dto.request.ApplicationRequestDTO;
import com.find_jobs.application_process_service.dto.response.ApplicationResponseDTO;
import com.find_jobs.application_process_service.dto.response.CompanyResponseDTO;
import com.find_jobs.application_process_service.dto.response.JobResponseDTO;
import com.find_jobs.application_process_service.entity.Application;
import com.find_jobs.application_process_service.entity.ApplicationStatusHistory;
import com.find_jobs.application_process_service.entity.enums.ApplicationStatus;
import com.find_jobs.application_process_service.exception.NotFoundException;
import com.find_jobs.application_process_service.repository.ApplicationProcessRepository;
import com.find_jobs.application_process_service.util.Response;
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

    @Autowired
    CompanyServiceClient companyServiceClient;

    @Autowired
    JobServiceClient jobServiceClient;

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


        Response<CompanyResponseDTO> company = companyServiceClient.getCompanyProfile(application.getCompanyId());
        Response<JobResponseDTO> job = jobServiceClient.getJobById(application.getJobId());
        List<ApplicationStatusHistory> applicationStatusHistoryList = applicationStatusHistoryService.getHistoryByApplicationId(application.getId());

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(mapToApplicationResponseDTO(application, job.getData(), company.getData(), applicationStatusHistoryList))
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

        String statusString = status != null ? status.toString() : null;

        Page<Application> applications = applicationProcessRepository.getApplicationsByApplicantId(applicantId, statusString, pageable);

        List<ApplicationResponseDTO> applicationResponseDTOList = applications.stream()
                .map(application -> {
                    Response<CompanyResponseDTO> company = companyServiceClient.getCompanyProfile(application.getCompanyId());
                    Response<JobResponseDTO> job = jobServiceClient.getJobById(application.getJobId());

                    return ApplicationResponseDTO.builder()
                            .id(application.getId())
                            .job(job.getData())
                            .applicantId(application.getApplicantId())
                            .company(company.getData())
                            .status(application.getStatus())
                            .appliedAt(application.getAppliedAt())
                            .updatedAt(application.getUpdatedAt())
                            .resumeUrl(application.getResumeUrl())
                            .coverLetter(application.getCoverLetter())
                            .build();

                }).toList();

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

        String statusString = status != null ? status.toString() : null;

        Page<Application> applications = applicationProcessRepository.getApplicationsByCompanyId(companyId, statusString, pageable);

        List<ApplicationResponseDTO> applicationResponseDTOList = applications.stream()
                .map(application -> {
                    Response<CompanyResponseDTO> company = companyServiceClient.getCompanyProfile(application.getCompanyId());
                    Response<JobResponseDTO> job = jobServiceClient.getJobById(application.getJobId());

                    return ApplicationResponseDTO.builder()
                            .id(application.getId())
                            .job(job.getData())
                            .applicantId(application.getApplicantId())
                            .company(company.getData())
                            .status(application.getStatus())
                            .appliedAt(application.getAppliedAt())
                            .updatedAt(application.getUpdatedAt())
                            .resumeUrl(application.getResumeUrl())
                            .coverLetter(application.getCoverLetter())
                            .build();

                }).toList();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(applicationResponseDTOList)
                .build();
    }


    private ApplicationResponseDTO mapToApplicationResponseDTO(Application application, JobResponseDTO jobResponseDTO, CompanyResponseDTO companyResponseDTO, List<ApplicationStatusHistory> applicationStatusHistoryList) {
        return ApplicationResponseDTO.builder()
                .id(application.getId())
                .job(jobResponseDTO)
                .applicantId(application.getApplicantId())
                .company(companyResponseDTO)
                .status(application.getStatus())
                .tracking(applicationStatusHistoryList)
                .appliedAt(application.getAppliedAt())
                .updatedAt(application.getUpdatedAt())
                .resumeUrl(application.getResumeUrl())
                .coverLetter(application.getCoverLetter())
                .build();
    }


}
