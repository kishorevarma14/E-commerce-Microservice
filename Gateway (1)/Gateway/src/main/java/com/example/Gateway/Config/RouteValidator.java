package com.example.Gateway.Config;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> openapiendpoints=List.of("/auth/login","/auth/register");
    public Predicate<ServerHttpRequest> Secured= request->openapiendpoints.stream().noneMatch(uri->request.getURI().getPath().contains(uri));

}
