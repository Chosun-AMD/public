package com.example.amd_project.Domain.User.DTO;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDTO implements Serializable {
    private int status;
    private String code;
    private String message;
    private LoginResponseBodyDTO data;
}