package com.find_jobs.job_service.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig {

//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//                final RequestAttributes requestAttributes =
//                        RequestContextHolder.getRequestAttributes();
//
//                if (requestAttributes != null) {
//                    final HttpServletRequest httpServletRequest =
//                            ((ServletRequestAttributes)
//                                    requestAttributes).getRequest();
//
//                    requestTemplate.header(HttpHeaders.AUTHORIZATION,
//                            httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
//
//                }
//            }
//        };
//    }
}