package com.find_jobs.user_profile_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
