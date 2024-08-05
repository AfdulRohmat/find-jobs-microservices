package com.find_jobs.user_profile_service.constant;

public interface Constant {


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
    }

}
