package com.nsh.services.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (final HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests (requests -> requests
                        .requestMatchers(
                                HttpMethod.GET,
                                "/styles/css/login.css")
                        .permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/styles/css/lamp.css",
                                "/jquery/jquery.js"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/lamps/add",
                                "/lamps/canceled/**",
                                "/lamps/broken/**",
                                "/lamps/error/**",
                                "/timestamp"
                        ).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(
                                HttpMethod.GET,
                                "/lamps/repair",
                                "/lamps/repair/**",
                                "/lamps/inProcess/**",
                                "/job-nomenclatures",
                                "/material-nomenclatures").hasAnyRole("ADMIN", "TECHNICAL")
                        .requestMatchers(HttpMethod.POST, "/lamps/add").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/lamp-submit").hasAnyRole("ADMIN", "TECHNICAL")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/lamps/add")
                        .permitAll())
                .logout((logout) -> logout
                        .permitAll())
                .httpBasic();

        return httpSecurity.build();
    }
}
