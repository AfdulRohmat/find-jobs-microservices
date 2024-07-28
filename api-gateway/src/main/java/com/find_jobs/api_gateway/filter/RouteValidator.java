package com.find_jobs.api_gateway.filter;


import com.find_jobs.api_gateway.constant.Constant;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class RouteValidator {

    public Predicate<ServerHttpRequest> isSecure = request -> Constant.AUTH_WHITELIST.stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
