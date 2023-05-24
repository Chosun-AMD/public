package com.example.amd_project.Domain.User.Controller;

import com.example.amd_project.Domain.User.DTO.RequestUserRegisterDTO;
import com.example.amd_project.Domain.User.DTO.ResponseUserRegisterDTO;
import com.example.amd_project.Domain.User.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String signUp(Model model, @Valid RequestUserRegisterDTO requestUserRegisterDTO){
        ResponseEntity<ResponseUserRegisterDTO> status = userServiceImpl.signUp(requestUserRegisterDTO);
        if(status.getStatusCode() == HttpStatus.OK){
            model.addAttribute("message", "회원가입에 성공했습니다.");
            model.addAttribute("linkUrl", "/user/loginform");
        }
        else if(status.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
            model.addAttribute("message", "Email이나 Name이 이미 존재합니다.");
            model.addAttribute("linkUrl", "/user/signup");
        }else{
            model.addAttribute("message", "잘못된 요청입니다.");
            model.addAttribute("linkUrl", "/user/signup");
        }
        return "resultPage";
    }

    @GetMapping("/loginform")
    public String loginPage(){
        return "user/loginform";
    }

    @GetMapping("/loginF")
    public String loginF(Model model){
        model.addAttribute("message", "잘못된 로그인 정보입니다.");
        model.addAttribute("linkUrl", "/user/loginform");
        return "user/loginF";
    }
}
