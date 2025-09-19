package com.example.E_commerce_cart.OpenFeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Interceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.getCredentials() instanceof String)
        {
            String JwtToken=(String)authentication.getCredentials();
            requestTemplate.header("Authorization","Bearer "+JwtToken);
        }
    }
}
