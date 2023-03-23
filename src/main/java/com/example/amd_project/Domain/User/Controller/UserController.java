package com.example.amd_project.Domain.User.Controller;

import com.example.amd_project.Domain.User.DTO.RequestUserRegisterDTO;
import com.example.amd_project.Domain.User.DTO.ResponseUserDTO;
import com.example.amd_project.Domain.User.Entity.Users;
import com.example.amd_project.Domain.User.Repository.UserRepository;
import com.example.amd_project.Domain.User.Service.UserService;
import com.example.amd_project.Global.Result.ResultCode;
import com.example.amd_project.Global.Result.ResultResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private final UserRepository userRepository;

    // 객체 주입(초기화)
    @Autowired
    public UserController(UserService userService,
                          UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RequestUserRegisterDTO user){
        ResponseUserDTO responseUserDTO = userService.signup(user);
        ResultResponse result = ResultResponse.of(ResultCode.REGISTER_SUCCESS, responseUserDTO);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }
}
