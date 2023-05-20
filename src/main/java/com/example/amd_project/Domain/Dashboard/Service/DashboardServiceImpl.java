package com.example.amd_project.Domain.Dashboard.Service;

import com.example.amd_project.Domain.Dashboard.DTO.ResponseDataDTO;
import com.example.amd_project.Domain.User.DTO.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService{
    private final RestTemplate restTemplate;

    @Value("${spring.dashboard.url}")
    String dataUrl;

    public List<ResponseDataDTO> getData() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ResponseDataDTO[]> response = restTemplate.exchange(
                dataUrl + "/dashboard",
                HttpMethod.POST,
                entity,
                ResponseDataDTO[].class
        );
        log.info("response={}", Arrays.toString(response.getBody()));
        return Arrays.asList(response.getBody());
    }
}
