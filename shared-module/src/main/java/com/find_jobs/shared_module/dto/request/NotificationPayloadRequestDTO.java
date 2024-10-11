package com.find_jobs.shared_module.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationPayloadRequestDTO {
    private String email;
    private String subject;
    private String message;
}