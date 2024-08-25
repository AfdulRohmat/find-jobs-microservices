package com.find_jobs.api_gateway.config;


import com.find_jobs.api_gateway.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, JwtAuthenticationFilter jwtAuthenticationFilter) {
        return builder.routes()
                .route("auth-service", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://AUTH-SERVICE"))
                .route("applicant-profile-service", r -> r.path("/api/v1/applicant-profiles/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://APPLICANT-PROFILE-SERVICE"))
                .route("company-service", r -> r.path("/api/v1/company-profiles/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://COMPANY-SERVICE"))
                .build();
    }
}