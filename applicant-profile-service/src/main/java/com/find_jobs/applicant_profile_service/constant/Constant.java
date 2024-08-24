package com.find_jobs.applicant_profile_service.constant;

public interface Constant {

    String[] AUTH_WHITELIST = {
//            "/swagger-ui/**",
//            "/api-docs/**",
//            "/swagger-ui.html",

            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/auth/validateAccessToken"
    };

    class Response {
        public static final int SUCCESS_CODE = 200;
        public static final String SUCCESS_MESSAGE = "Success";
        public static final String CREATE_SUCCESS_MESSAGE = "Data successfully added";
        public static final String UPDATE_SUCCESS_MESSAGE = "Data successfully updated";
        public static final String DELETE_SUCCESS_MESSAGE = "Data successfully deleted";
    }

    class Message {
        public static final String EXIST_DATA_MESSAGE = "data already exist";
        public static final String NOT_FOUND_DATA_MESSAGE = "data not found";
        public static final String INVALID_LOGIN_MESSAGE = "Username / Password wrong";
        public static final String INVALID_TOKEN_MESSAGE = "Invalid access token";
    }

}
