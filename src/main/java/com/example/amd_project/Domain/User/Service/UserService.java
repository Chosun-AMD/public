package com.example.amd_project.Domain.User.Service;

import com.example.amd_project.Domain.User.DTO.RequestUserRegisterDTO;
import com.example.amd_project.Domain.User.DTO.ResponseUserDTO;
import com.example.amd_project.Domain.User.Entity.Users;
import com.example.amd_project.Domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public ResponseUserDTO signup(RequestUserRegisterDTO requestUserRegisterDTO) {
        Users users = requestUserRegisterDTO.toEntity(new Date());
        Users save = userRepository.save(users);

        return ResponseUserDTO.of(save);
    }
}
