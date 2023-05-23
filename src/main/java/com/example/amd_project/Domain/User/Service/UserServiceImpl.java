package com.example.amd_project.Domain.User.Service;

import com.example.amd_project.Domain.User.DTO.*;
import com.example.amd_project.Domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
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
    @Value("${spring.auth.url}")
    private String authUrl;
    private final RestTemplate restTemplate;

    /**
     * 회원가입 기능 구현
     * 황시준
     *
     * @param requestUserRegisterDTO
     * @return
     */
    @Override
    public ResponseEntity<ResponseUserRegisterDTO> signUp(RequestUserRegisterDTO requestUserRegisterDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestUserRegisterDTO> entity = new HttpEntity<>(requestUserRegisterDTO, headers);

        try {
            ResponseEntity<ResponseUserRegisterDTO> response = restTemplate.exchange(
                    authUrl + "/signup",
                    HttpMethod.POST,
                    entity,
                    new ParameterizedTypeReference<>() {}
            );
            if(response.getStatusCode() == HttpStatus.OK){
                ResponseUserRegisterDTO responseBody = response.getBody();
                return ResponseEntity.ok(responseBody);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            } else {
                return ResponseEntity.status(e.getStatusCode()).build();
            }
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
