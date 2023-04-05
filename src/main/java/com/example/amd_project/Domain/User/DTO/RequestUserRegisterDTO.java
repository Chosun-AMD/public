package com.example.amd_project.Domain.User.DTO;

import com.example.amd_project.Domain.User.Entity.Users;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserRegisterDTO {
    // 회원가입 시 사용할 DTO
    @NotBlank(message = "이메일이 비어있습니다.")
    private String email;   // 이메일

    @Size(min=8, message="PW는 8글자 이상 입력해 주세요.")
    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String pwd;     // 비밀번호

    @NotBlank(message = "이름이 비어있습니다.")
    private String name;    // 이름

    public Users toEntity(Date now){
        return Users.builder()
                .email(getEmail())
                .pwd(getPwd())
                .name(getName())
                .createAt(now)
                .build();
    }
}
