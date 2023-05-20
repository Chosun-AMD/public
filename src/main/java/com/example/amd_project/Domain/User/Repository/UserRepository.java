package com.example.amd_project.Domain.User.Repository;

import com.example.amd_project.Domain.User.DTO.RequestUserRegisterDTO;
import com.example.amd_project.Domain.User.DTO.ResponseUserRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface UserRepository{
    ResponseEntity<ResponseUserRegisterDTO> signUp(RequestUserRegisterDTO request);
    //0ResponseUserLoginDTO login(RequestUserLoginDTO userLoginDTO);
}
