package org.example.springproductmanagment.config;

import org.example.springproductmanagment.SpringProductManagmentApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
                                .requestMatchers("/api/login").permitAll()
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl(("/api/login"))
                        .successHandler(((request, response, authentication) -> response.setStatus(200)))
                        .failureHandler((request, response, exception) -> response.setStatus(401))

                );

        return http.build();
    }


}
