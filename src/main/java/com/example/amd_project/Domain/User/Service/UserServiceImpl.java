package com.example.amd_project.Domain.User.Service;

import com.example.amd_project.Domain.User.DTO.*;
import com.example.amd_project.Domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 회원 등록
 * @author 황시준
 * @since 1.0
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserRepository {
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 기능 구현
     * 황시준
     * @param requestUserRegisterDTO
     * @return
     */
    @Override
        public ResponseUserRegisterDTO signUp(RequestUserRegisterDTO requestUserRegisterDTO) {
            String signupurl = "http://localhost:9000/auth/signup";
            requestUserRegisterDTO.setPwd(passwordEncoder.encode(requestUserRegisterDTO.getPwd()));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<RequestUserRegisterDTO> entity = new HttpEntity<>(requestUserRegisterDTO, headers);

        ResponseEntity<ResponseDTO<ResponseUserRegisterDTO>> response = restTemplate.exchange(
                signupurl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );

        log.info("response={}", response.getBody().getData());
        return response.getBody().getData();
    }

    @Override
    public ResponseUserLoginDTO login(RequestUserLoginDTO requestUserLoginDTO){
        String loginurl = "http://localhost:9000/auth/login";
        log.info("Email = {}", requestUserLoginDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity<>(requestUserLoginDTO, headers);

        ResponseEntity<ResponseDTO<ResponseUserLoginDTO>> response = restTemplate.exchange(
                loginurl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody().getData();
    }
}
