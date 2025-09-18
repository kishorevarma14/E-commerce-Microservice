package com.example.Gateway.Filter;

import com.example.Gateway.Config.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtFilter jwtFilter;
    public AuthenticationFilter()
    {
        super(Config.class);
    }
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest modifiedRequest=null;
            if (routeValidator.Secured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    try {
                        throw new Exception("Invalid Authorization Header");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                String AuthHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (AuthHeader.contains("Bearer ")) {
                    AuthHeader = AuthHeader.substring(7);
                }
                try {
                    jwtFilter.validatetoken(AuthHeader);
                    String username = jwtFilter.extractUsername(AuthHeader);
                    Set<String> roles=jwtFilter.extractRoles(AuthHeader);
                    modifiedRequest = exchange.getRequest().mutate().header("loggedInUser", username).header("roles",String.join(",",roles)).build();
                } catch (Exception e) {
                    System.out.println("Invalid Or Expired JWT Token"+e.getMessage());
                }
            }
                assert modifiedRequest!=null;
                return chain.filter(exchange.mutate().request(modifiedRequest).build());
        });
    }
        public static class Config
        {

        }
}

