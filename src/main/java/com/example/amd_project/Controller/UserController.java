package com.example.amd_project.Controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
    private Environment env;

    @GetMapping("/health_check")
    public String status(){
        System.out.println("Status");
        return String.format("It's Working in AMD User Service on PORT  %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome(){
        //return env.getProperty("greeting.message");     // application.yml 파일에 있는 greeting.message를 가져옴.
        return "Welcome AMD Server";

        //return greeting.getMessage();
    }
}
