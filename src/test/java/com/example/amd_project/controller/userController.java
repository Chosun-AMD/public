package com.example.amd_project.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {

    @GetMapping("/signup")
    public String signup(){
        return "user/signup.html";
    }
}
