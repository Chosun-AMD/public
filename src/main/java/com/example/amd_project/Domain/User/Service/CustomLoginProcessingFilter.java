package com.example.amd_project.Domain.User.Service;

import com.example.amd_project.Domain.User.DTO.RequestUserRegisterDTO;
import com.example.amd_project.Domain.User.DTO.ResponseDTO;
import com.example.amd_project.Domain.User.DTO.ResponseUserRegisterDTO;
import com.example.amd_project.Global.Exception.InvalidLoginRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class CustomLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public CustomLoginProcessingFilter(String processingUrl) {
        super(processingUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException{
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");

        if(Objects.isNull(email) || Objects.isNull(pwd)){
            throw new InvalidLoginRequestException();
        }

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                email,
                pwd
        ));
    }
}
