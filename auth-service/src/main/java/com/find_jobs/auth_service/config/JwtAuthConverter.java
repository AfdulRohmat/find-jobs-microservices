package com.find_jobs.auth_service.config;


import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter  {

//    @Value("${jwt.auth.converter.resource-id}")
//    private String resourceId;
//    @Value("${jwt.auth.converter.principle-attribute}")
//    private String principleAttribute;
//
//    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//
//    @Override
//    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
//
//        Collection<GrantedAuthority> authorities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
//                extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
//        return new JwtAuthenticationToken(jwt, authorities,
//                getPrincipalName(jwt));
//    }
//
//    private String getPrincipalName(Jwt jwt) {
//        String claimName = JwtClaimNames.SUB;
//        if (Objects.nonNull(principleAttribute)) {
//            claimName = principleAttribute;
//        }
//        return jwt.getClaim(claimName);
//
//    }
//
//    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
////        Map<String, Object> resourceAccess;
////        Map<String, Object> resource;
////        Collection<String> resourceRoles;
////
////        if (Objects.isNull(jwt.getClaim("resource_access"))) {
////            return Set.of();
////        }
////
////        resourceAccess = jwt.getClaim("resource_access");
////        if (Objects.isNull(resourceAccess.get(resourceId))) {
////            return Set.of();
////        }
////        resource = (Map<String, Object>) resourceAccess.get(resourceId);
////        resourceRoles = (Collection<String>) resource.get("roles");
//        return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
//    }
}