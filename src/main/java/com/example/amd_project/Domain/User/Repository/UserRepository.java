package com.example.amd_project.Domain.User.Repository;

import com.example.amd_project.Domain.User.DTO.RequestUserLoginDTO;
import com.example.amd_project.Domain.User.DTO.RequestUserRegisterDTO;
import com.example.amd_project.Domain.User.DTO.ResponseUserLoginDTO;
import com.example.amd_project.Domain.User.DTO.ResponseUserRegisterDTO;

public interface UserRepository{
    ResponseUserRegisterDTO signUp(RequestUserRegisterDTO request);
    ResponseUserLoginDTO login(RequestUserLoginDTO userLoginDTO);
}
