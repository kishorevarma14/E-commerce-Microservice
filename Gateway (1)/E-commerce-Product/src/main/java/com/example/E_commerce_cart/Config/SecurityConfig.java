package com.example.E_commerce_cart.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    roleFilter filter;
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(authorize->authorize.anyRequest().authenticated())
                .csrf(customizer-> customizer.disable())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(customizer -> customizer.disable())
                .sessionManagement(customizer->customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(customizer->{});
        return security.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            throw new UsernameNotFoundException("No user service - only JWT is supported");
        };
    }
}
