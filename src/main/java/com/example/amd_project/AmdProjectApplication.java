package com.example.amd_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class AmdProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmdProjectApplication.class, args);
    }

    @GetMapping("/ip")
    public Map<String, String> ip(HttpServletRequest request) throws UnknownHostException {
        return new HashMap<>() {{
            put("hostIp", InetAddress.getLocalHost().getHostAddress());
            put("accessIp", request.getHeader("x-forwarded-for"));
        }};
    }

    @GetMapping("/health_check")
    public String health_check(){
        return "Health_check";
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
