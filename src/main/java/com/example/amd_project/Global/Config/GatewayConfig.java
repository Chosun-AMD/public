package com.example.amd_project.Global.Config;

import lombok.Getter;
import lombok.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class GatewayConfig {
    @Value("${AMD-Public.gateway.base}")
    private String url;
}
