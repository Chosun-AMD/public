package com.example.amd_project.Global.Config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 로그인 성공 후 처리 로직을 구현
        // 특정 페이지로 이동하는 경우, redirect 또는 forward를 사용하여 이동할 수 있음
        response.sendRedirect("/dashboard"); // "/myPage"는 이동할 URL 경로
    }

}