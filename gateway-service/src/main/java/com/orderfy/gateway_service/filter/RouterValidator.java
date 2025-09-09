package com.orderfy.gateway_service.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> publicEndpoints = List.of(
            "/api/v1/auth/login",
            "/api/v1/auth/guest-session",
            "/api/v1/employees",
            "/api/v1/restaurants"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> publicEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}