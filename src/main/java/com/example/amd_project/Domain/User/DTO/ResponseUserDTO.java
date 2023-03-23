package com.example.amd_project.Domain.User.DTO;

import com.example.amd_project.Domain.User.Entity.Users;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor // 각 블록마다 객체 생성
@NoArgsConstructor // 기본 생성자
public class ResponseUserDTO
{
    // 응답에 사용할 DTO
    private String email;   // 이메일
    private String name;    // 이름

    public static ResponseUserDTO of(Users users){
        return new ResponseUserDTO(users.getEmail(), users.getName());
    }
}
