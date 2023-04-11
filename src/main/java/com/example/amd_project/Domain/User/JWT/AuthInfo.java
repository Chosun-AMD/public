package com.example.amd_project.Domain.User.JWT;

import com.example.amd_project.Domain.User.DTO.ResponseUserDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo implements Serializable {
    private String userId;
    private String email;
    private String accessToken;
    private List<String> authorities;
    private String refreshToken;
    private Long accessTokenExpiresIn;

    public AuthInfo(
            ResponseUserDTO responseUserDTO,
            String accessToken,
            List<String> authorities,
            String refreshToken,
            Long accessTokenExpiresIn
    ) {
        this.userId = responseUserDTO.getUserId();
        this.email = responseUserDTO.getEmail();
        this.accessToken = accessToken;
        this.authorities = getAuthorities();
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}

