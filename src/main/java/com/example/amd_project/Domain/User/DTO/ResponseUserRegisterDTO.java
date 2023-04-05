package com.example.amd_project.Domain.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 회원 등록 이후 UserController에서 Client에게 반환하기 위한 DTO
 *
 * @author 황시준
 * @since 1.0
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserRegisterDTO {
    private Long id;
    private String name;
}
