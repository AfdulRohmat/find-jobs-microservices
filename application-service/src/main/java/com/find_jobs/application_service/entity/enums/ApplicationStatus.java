package com.find_jobs.application_service.entity.enums;

public enum ApplicationStatus {
    APPLIED,       // When the applicant has initially applied for the job
    REVIEWING,     // When the application is being reviewed by the employer
    INTERVIEW,     // When the applicant is scheduled for or has completed an interview
    OFFERED,       // When an offer has been made to the applicant
    ACCEPTED,      // When the applicant has accepted the offer
    REJECTED,      // When the application has been rejected
}