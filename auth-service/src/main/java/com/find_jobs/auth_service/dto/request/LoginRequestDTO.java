package com.find_jobs.auth_service.dto.request;

import com.find_jobs.auth_service.constant.Constant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "email is mandatory, please fill it!")
    @NotNull(message = "email mandatory, please fill it!")
    private String email;

    @NotBlank(message = "Password is mandatory, please fill it!")
    @NotNull(message = "Password mandatory, please fill it!")
    private String password;

}