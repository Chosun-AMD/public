package com.example.amd_project.Domain.User.Controller;

import com.example.amd_project.Domain.User.DTO.RequestUserRegisterDTO;
import com.example.amd_project.Domain.User.Repository.UserRepository;
import com.example.amd_project.Domain.User.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userServiceImpl;

    // 객체 주입(초기화)
    @Autowired
    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signup(){
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid RequestUserRegisterDTO requestUserRegisterDTO){
        userServiceImpl.signUp(requestUserRegisterDTO);
        return "user/login-page";
    }

    @GetMapping("/login-page")
    public String loginPage(){
        return "user/login-page";
    }
}
