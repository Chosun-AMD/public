package com.example.amd_project.Global.Config;

import com.example.amd_project.Domain.User.Adapter.UserAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final UserAdapter userAdapter;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        HttpSession session = request.getSession(false);

        if(Objects.isNull(session)){
            log.info("Already Loged Out");
            return;
        }

        session.invalidate();       // 세션 제거
    }
}
