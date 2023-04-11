package com.example.amd_project.Domain.User.Adapter;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.example.amd_project.Domain.User.DTO.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter {
    private final RestTemplate restTemplate;

    /**
     * 로그인 과정에서 Auth Server에서 인증도니 JWT 형식의 accessToken과 uuid를 받아오는 기능
     * 해당 정보들은 HTTP Response Header에 담겨 반환됨
     *
     * @param RequestUserLoginDTO 회원이 로그인 시 입력한 정보를 담은 DTO
     * @return Auth Server에서 발급받은 JWT 형식의 accessToken
     * @author 황시준
     * @since 1.0
     */

    public ResponseEntity<LoginResponseDTO> getAuthInfo(RequestUserLoginDTO requestUserLoginDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        HttpEntity<RequestUserLoginDTO> entity = new HttpEntity<>(requestUserLoginDTO, headers);

        return restTemplate.exchange(
                "http://localhost:9000/auth/login",
                HttpMethod.POST,
                entity,
                LoginResponseDTO.class
            );
    }

    /**
     * 로그인 과정에서 accessToken을 받급 받아 HTTP Header에 추가한 후 회원정보를 요청하는 기능
     * @param RequestUserLoginDTO 회원 로그인 시 입력한 정보를 담는 DTO
     * @return 회원 정보 DTO를 반환
     * @author 황시준
     * @since 1.0
     */
    public ResponseEntity<ResponseDTO<ResponseUserDTO>> getUserInfo(
            RequestUserLoginDTO requestUserLoginDTO,
            String accessToken
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken); // Bearer 토큰 설정

        int portNum = 9000;     // localhost:9000으로 통신할 포트 지정(나중에 삭제)
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost")
                .port(portNum)
                .path("/user/{userId}")
                .encode()
                .build()
                .expand(requestUserLoginDTO.getUserId())
                .toUri();

        System.out.println("uri : " + uri);

        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                new ParameterizedTypeReference<>() {
                }
        );
    }
}
