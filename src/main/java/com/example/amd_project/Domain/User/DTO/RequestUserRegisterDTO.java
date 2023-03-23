package com.example.amd_project.Domain.User.DTO;

import com.example.amd_project.Domain.User.Entity.Users;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class RequestUserRegisterDTO {
    // 회원가입 시 사용할 DTO
    @ApiModelProperty(value = "이메일", example = "test@test.com", required = true)
    @NotBlank(message = "이메일이 비어있습니다.")
    private String email;   // 이메일

    @ApiModelProperty(value = "비밀번호", example = "password1234", required = true)
    @Size(min=8, message="PW는 8글자 이상 입력해 주세요.")
    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String pwd;     // 비밀번호

    @ApiModelProperty(value = "이름", example = "Ramos", required = true)
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
