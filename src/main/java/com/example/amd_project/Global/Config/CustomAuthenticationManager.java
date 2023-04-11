package com.example.amd_project.Global.Config;

import com.example.amd_project.Domain.User.Adapter.UserAdapter;
import com.example.amd_project.Domain.User.DTO.*;
import com.example.amd_project.Domain.User.JWT.AuthInfo;
import com.example.amd_project.Global.Exception.InvalidHttpHeaderException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserAdapter userAdapter;
    //private final String UUID_HEADER = "UUID-HEADER";
    //private static final String X_EXPIRE_HEADER = "X-Expire";
    /**
     * 인증 서버에서 발급받은 JWT 토큰을 기반으로 유저 정보를 요청한 후 UsernamePasswordAuthenticationToken을 만들어 반환
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


        ResponseEntity<LoginResponseDTO> exchange = userAdapter.getAuthInfo(requestUserLoginDTO);
        checkValidLoginRequest(exchange);

        LoginResponseDTO loginResponseDTO = exchange.getBody();
        String accessToken = loginResponseDTO.getData().getAccessToken();       // Get Access Token
        String refreshToken = loginResponseDTO.getData().getRefreshToken();     // Get Refresh Token
        Long accessTokenExpiresIn = loginResponseDTO.getData().getAccessTokenExpiresIn();   // Get Token Expire Time

        log.info("accessToken={}", accessToken);
        log.info("refreshToken={}", refreshToken);
        log.info("accessTokenExpiresIn={}", accessTokenExpiresIn);

        // 로그인 이후 발급받은 accessToken을 가지고 user정보를 가져오는 과정
        ResponseEntity<ResponseDTO<ResponseUserDTO>> response = userAdapter.getUserInfo(
                requestUserLoginDTO,
                accessToken
        );

        ResponseUserDTO responseUserDTO = response.getBody().getData();

        List<SimpleGrantedAuthority> authorities = getAuthorities(responseUserDTO);
        //GrantedAuthority authority = new SimpleGrantedAuthority(userResponseDTO.getRoles());
        log.info("authorities={}", authorities);


        AuthInfo authInfo = new AuthInfo(
                responseUserDTO,
                accessToken,
                // responseUserDTO.getAuthority(),
                responseUserDTO.getAuthority(),
                refreshToken,
                accessTokenExpiresIn
        );

        log.info("authInfo={}", authInfo);

        return new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal().toString(),
                null,
                authorities
        );
    }

    private List<SimpleGrantedAuthority> getAuthorities(ResponseUserDTO responseUserDTO) {
        ResponseUserDTO user = Objects.requireNonNull(responseUserDTO);
        log.info("member={}", user);

        return user.getAuthority().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }
    /**
     * 로그인 요청 시 올바른 결과인지 판별하기 위해 Response Header를 검증하는 기능
     * 예외 발생 시 CustomFailuserHandler가 동작
     *
     * @param exchange Auth 서버에 login요청 시 반환되는 결과
     * @author : 황시준
     * @since : 1.0
     */
    private void checkValidLoginRequest(ResponseEntity<LoginResponseDTO> exchange){
        log.info("Auth Server Excnahge Check={}", exchange);

        /*
        if(!exchange.getHeaders())
            throw new BadCredentialsException("자격 증명 실패");
        }
        */
        if(exchange.getStatusCode().equals(200)){
            throw new BadCredentialsException("자격 증명 실패");
        }
    }

    /**
     * 로그인 시 Auth 서버에서 제공받은 응답 Header를 추출하는 기능
     *
     * @param exchange Auth 서버에서 발급받은 accessToken,refreshToken을 Parseing
     * @return Http Response Body에 들어있는 accessToken을 추출하여 반환
     * @author : 황시준
     * @since : 1.0
     */

    private String extractAuthorizationBody(ResponseEntity<Void> exchange) {
        String accessToken = Objects.requireNonNull(exchange.getBody().toString());
        if (Objects.isNull(accessToken)) {
            throw new InvalidHttpHeaderException("AccessToken is empty");
        }
        return accessToken;
    }

}
