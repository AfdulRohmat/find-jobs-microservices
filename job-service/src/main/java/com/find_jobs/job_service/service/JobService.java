package com.find_jobs.job_service.service;

import com.find_jobs.job_service.constant.Constant;
import com.find_jobs.job_service.dto.request.JobRequestDTO;
import com.find_jobs.job_service.dto.response.JobResponseDTO;
import com.find_jobs.job_service.entity.Job;
import com.find_jobs.job_service.exception.NotFoundException;
import com.find_jobs.job_service.repository.JobRepository;
import com.find_jobs.job_service.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Transactional
    public Response<Object> createJob(JobRequestDTO jobRequestDTO) {
        Job job = new Job();

        job.setTitle(jobRequestDTO.getTitle());
        job.setDescription(jobRequestDTO.getDescription());
        job.setLocation(jobRequestDTO.getLocation());
        job.setCompanyName(jobRequestDTO.getCompany());
        job.setSalary(jobRequestDTO.getSalary());
        job.setEmploymentType(jobRequestDTO.getEmploymentType());
        job.setExperienceLevel(jobRequestDTO.getExperienceLevel());
        job.setYearsOfExperience(jobRequestDTO.getYearsOfExperience());
        job.setPostedDate(jobRequestDTO.getPostedDate());
        job.setExpiryDate(jobRequestDTO.getApplicationDeadline());
        job.setSkills(jobRequestDTO.getSkills());
        job.setIndustry(jobRequestDTO.getIndustry());
        job.setJobFunction(jobRequestDTO.getJobFunction());
        job.setEducationLevel(jobRequestDTO.getEducationLevel());

        Job savedJob = jobRepository.save(job);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(savedJob)
                .build();
    }

    @Transactional
    public Response<Object> getAllJobs() {
        List<Job> getAllJobs = jobRepository.findAll();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(getAllJobs)
                .build();
    }

    @Transactional
    public Response<Object> getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Data not found"));

        JobResponseDTO data = JobResponseDTO.builder()
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .company(job.getCompanyName())
                .salary(job.getSalary())
                .employmentType(job.getEmploymentType())
                .experienceLevel(job.getExperienceLevel())
                .yearsOfExperience(job.getYearsOfExperience())
                .postedDate(job.getPostedDate())
                .applicationDeadline(job.getExpiryDate())
                .skills(job.getSkills())
                .industry(job.getIndustry())
                .jobFunction(job.getJobFunction())
                .educationLevel(job.getEducationLevel())
                .build();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(data)
                .build();
    }

    @Transactional
    public Response<Object> updateJob(Long id, JobRequestDTO jobRequestDTO) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Data not found"));

        job.setTitle(jobRequestDTO.getTitle());
        job.setDescription(jobRequestDTO.getDescription());
        job.setLocation(jobRequestDTO.getLocation());
        job.setCompanyName(jobRequestDTO.getCompany());
        job.setSalary(jobRequestDTO.getSalary());
        job.setEmploymentType(jobRequestDTO.getEmploymentType());
        job.setExperienceLevel(jobRequestDTO.getExperienceLevel());
        job.setYearsOfExperience(jobRequestDTO.getYearsOfExperience());
        job.setPostedDate(jobRequestDTO.getPostedDate());
        job.setExpiryDate(jobRequestDTO.getApplicationDeadline());
        job.setSkills(jobRequestDTO.getSkills());
        job.setIndustry(jobRequestDTO.getIndustry());
        job.setJobFunction(jobRequestDTO.getJobFunction());
        job.setEducationLevel(jobRequestDTO.getEducationLevel());

        Job updatedJob = jobRepository.save(job);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(updatedJob)
                .build();
    }

    @Transactional
    public Response<Object> deleteJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Data not found"));

        jobRepository.delete(job);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(null)
                .build();
    }
}
