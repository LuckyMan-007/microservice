package com.yon.order_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {

//    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  System.out.println("enter SecurityFilterChain::::::::::::");
        return http
//            .csrf(csrf -> csrf
//                .ignoringRequestMatchers("/h2-console/**")
//                .disable()
//            )
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/h2-console/**").permitAll()
//                .anyRequest().authenticated()
//            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )
//            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
