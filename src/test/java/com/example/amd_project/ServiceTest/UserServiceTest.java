package com.example.amd_project.ServiceTest;

import com.example.amd_project.Domain.User.DTO.RequestUserRegisterDTO;
import com.example.amd_project.Domain.User.DTO.ResponseDTO;
import com.example.amd_project.Domain.User.DTO.ResponseUserRegisterDTO;
import com.example.amd_project.Domain.User.Repository.UserRepository;
import com.example.amd_project.Domain.User.Service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before("")
    public void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testSignUp_Success() throws JsonProcessingException {
        // Mock 응답 설정
        ResponseUserRegisterDTO mockResponse = new ResponseUserRegisterDTO();
        ResponseEntity<ResponseDTO<ResponseUserRegisterDTO>> mockResponseEntity =
                ResponseEntity.ok(new ResponseDTO<>(mockResponse));

        // Mock 서버의 응답 설정
        mockServer.expect(requestTo("/signup"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(new ObjectMapper().writeValueAsString(mockResponseEntity),
                        MediaType.APPLICATION_JSON));

        // 테스트할 DTO 생성
        RequestUserRegisterDTO requestUserRegisterDTO = new RequestUserRegisterDTO();

        // 테스트 메서드 실행
        ResponseEntity<ResponseUserRegisterDTO> responseEntity = userService.signUp(requestUserRegisterDTO);

        // 결과 검증
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());

        mockServer.verify(); // 요청이 기대한대로 전송되었는지 확인
    }

}
