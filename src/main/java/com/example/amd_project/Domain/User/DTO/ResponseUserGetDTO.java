package com.example.amd_project.Domain.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseUserGetDTO implements Serializable {
    private int status;
    private String code;
    private String message;
    private ResponseUserDTO data;
}
