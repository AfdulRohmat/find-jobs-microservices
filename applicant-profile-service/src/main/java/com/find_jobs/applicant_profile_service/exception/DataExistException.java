package com.find_jobs.applicant_profile_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataExistException extends  RuntimeException{
    public DataExistException(String message) {
        super(message);
    }

    public DataExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
