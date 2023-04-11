package com.example.amd_project.Domain.User.DTO;

import lombok.*;

import java.util.List;

/**
 * 회원 정보 요청 시 결과를 받아오기 위한 DTO
 *
 * @author : 황시준
 * @since  : 1.0
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {
    private String userId;
    private String email;
    private String name;
    private String pwd;
    private List<String> authority;
    // private String authority;
}
