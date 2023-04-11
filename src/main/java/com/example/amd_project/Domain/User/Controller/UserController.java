package com.example.amd_project.Domain.User.Controller;

import com.example.amd_project.Domain.User.DTO.RequestUserLoginDTO;
import com.example.amd_project.Domain.User.DTO.RequestUserRegisterDTO;
import com.example.amd_project.Domain.User.DTO.ResponseUserRegisterDTO;
import com.example.amd_project.Domain.User.Repository.UserRepository;
import com.example.amd_project.Domain.User.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;

    // 객체 주입(초기화)
    @Autowired
    public UserController(UserServiceImpl userServiceImpl,
                          UserRepository userRepository){
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
    }

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signup(){
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid RequestUserRegisterDTO requestUserRegisterDTO){
        userServiceImpl.signUp(requestUserRegisterDTO);
        return "test";
    }


    @GetMapping("/loginform")
    public String login(){
        return "user/login";
    }

    /*
    @PostMapping("/login")
    public String login(RequestUserLoginDTO userDTO){
        userServiceImpl.login(userDTO);
        return "test";
    }
     */
}
