package com.example.user.login.Security;

import com.example.user.login.UserDetails.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    JwtAuthFilter jwtfilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth-> auth.requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated())
                .csrf(custom -> custom.disable())
                .formLogin(custom -> custom.disable())
                .httpBasic(customizer->{})
                .sessionManagement(customizer ->customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtfilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider AuthenicationProvider()
    {
        DaoAuthenticationProvider DaoObject=new DaoAuthenticationProvider();
        DaoObject.setPasswordEncoder(new BCryptPasswordEncoder());
        DaoObject.setUserDetailsService(userDetailsService);
        return DaoObject;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
