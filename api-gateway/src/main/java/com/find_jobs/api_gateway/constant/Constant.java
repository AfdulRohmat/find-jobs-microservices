package com.find_jobs.api_gateway.constant;


import java.text.SimpleDateFormat;
import java.util.List;

public interface Constant {

    List<String> AUTH_WHITELIST = List.of(
//            "/swagger-ui/**",
//            "/api-docs/**",
//            "/swagger-ui.html",

            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/auth/validateAccessToken",
            "/eureka"
    );

    class Message {
        public static final String FORBIDDEN_MESSAGE = "You don't have access";
        public static final String INVALID_TOKEN_MESSAGE = "Invalid access token";
    }

    class DateFormatter {
        public static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss");
    }
}
