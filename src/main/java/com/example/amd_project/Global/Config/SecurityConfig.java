package com.example.amd_project.Global.Config;

import com.example.amd_project.Domain.User.Adapter.UserAdapter;
import com.example.amd_project.Domain.User.Service.CustomLoginProcessingFilter;
import com.example.amd_project.Global.Exception.CustomFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserAdapter userAdapter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().permitAll();
        http.formLogin().loginPage("/user/login");

        http.addFilterAt(
                customLoginProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class
        );

        http.headers().defaultsDisabled().frameOptions().sameOrigin();
        http.csrf().disable();
        http.cors().disable();
        return http.build();
    }

    @Bean
    public CustomAuthenticationManager customAuthenticationManager(){
        return new CustomAuthenticationManager(userAdapter);
    }
    @Bean
    public CustomLoginProcessingFilter customLoginProcessingFilter(){
        CustomLoginProcessingFilter customLoginProcessingFilter = new CustomLoginProcessingFilter(
                "/user/login");
        customLoginProcessingFilter.setAuthenticationManager(customAuthenticationManager());
        customLoginProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler());

        return customLoginProcessingFilter;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomFailureHandler();
    }
}
