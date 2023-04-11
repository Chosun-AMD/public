package com.example.amd_project.Domain.User.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserLoginDTO {
    @NotBlank(message = "ID가 비어있습니다.")
    private String userId;

    @Size(min = 8, message = "PW는 최소 8자 이상 입력해야합니다.")
    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String pwd;
}
