package com.example.amd_project.Global.Config;

import com.example.amd_project.Domain.User.Adapter.UserAdapter;
import com.example.amd_project.Domain.User.DTO.*;
import com.example.amd_project.Domain.User.JWT.AuthInfo;
import com.example.amd_project.Global.Exception.InvalidHttpHeaderException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserAdapter userAdapter;
    // private final String ROLES;
    private final String UUID_HEADER = "UUID";
    private static final String X_EXPIRE_HEADER = "X-Expire";


    /**
     * 인증 서버에서 발급받은 JWT 토큰을 기반으로 유저 정보를 요청한 후 UsernamePasswordAuthenticationToken을 만들어 반환
     * 현재 Redis에 헤더에 담긴 유저정보를 저장하려고 함. 추후 업데이트 예정
     *
     * @param authentication 인증 객체
     * @return 인증 객체 반환
     * @throws AuthenticationException 인증 실패 시 발생하는 예외
     * @author 황시준
     * @since 1.0
     */
    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        RequestUserLoginDTO requestUserLoginDTO = new RequestUserLoginDTO(
                (String) authentication.getPrincipal(),
                (String) authentication.getCredentials()
        );
        ResponseEntity<Void> exchange = userAdapter.getAuthInfo(requestUserLoginDTO);
        checkValidLoginRequest(exchange);

        String uuid = Objects.requireNonNull(exchange.getHeaders().get(UUID_HEADER).get(0));        // UUID
        String expiredTime = Objects.requireNonNull(exchange.getHeaders().get(X_EXPIRE_HEADER).get(0));     // EXPIRE
        // String roles = Objects.requireNonNull(exchange.getHeaders().get(ROLES).get(0));         // ROLES

        String accessToken = extractAuthorizationHeader(exchange);      // Authorization

        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        log.info("accessToken={}", accessToken);
        
        // 로그인 이후 발급받은 accessToken을 가지고 user정보를 가져오는 과정
        ResponseEntity<ResponseDTO<ResponseUserDTO>> response = userAdapter.getUserInfo(
                requestUserLoginDTO,
                accessToken
        );

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        // authorities.add(new SimpleGrantedAuthority(roles); // "ROLE_USER"는 권한명, 필요에 따라 변경 가능

        String username = requestUserLoginDTO.getEmail();
        UserDetails userDetails = new User(username,"", authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),
                null,
                authentication.getAuthorities()
        );

        //return authenticationProvider.authenticate(authentication);
    }
    
    /**
     * 로그인 요청 시 올바른 결과인지 판별하기 위해 Response Header를 검증하는 기능
     * 예외 발생 시 CustomFailuserHandler가 동작
     *
     * @param exchange Auth 서버에 login요청 시 반환되는 결과
     * @author : 황시준
     * @since : 1.0
     */
    private void checkValidLoginRequest(ResponseEntity<Void> exchange) {
        log.info("Auth server exchange check={}", exchange);
        if (!exchange.getHeaders().containsKey(UUID_HEADER) || !exchange.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new BadCredentialsException("자격 증명 실패");
        }
    }
    /**
     * 로그인 시 Auth 서버에서 제공 받은 응답 헤더를 추출하는 기능입니다.
     *
     * @param exchange Auth 서버에서 제공받은 응답 헤더입니다.
     * @return Http Response Header의 Authorization에 들어있는 accessToken을 추출하여 반환합니다.
     * @author : 송학현
     * @since : 1.0
     */
    private String extractAuthorizationHeader(ResponseEntity<Void> exchange) {
        String accessToken = Objects.requireNonNull(exchange.getHeaders()
                .get(HttpHeaders.AUTHORIZATION)
                .get(0));
        if (Objects.isNull(accessToken)) {
            throw new InvalidHttpHeaderException("Authorization Header is empty");
        }
        return accessToken;
    }

}
