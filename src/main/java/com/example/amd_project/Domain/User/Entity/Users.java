package com.example.amd_project.Domain.User.Entity;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class Users {

    // 회원 아이디에 해당
    @Id
    @Column(name="user_email")
    private String email;

    @Column(name="user_pwd")
    private String pwd;

    @Column(name="user_name")
    private String name;

    @Column(name="create_date")
    private Date createAt;

    @Builder
    public Users(String email, String pwd, String name, Date createAt) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.createAt = createAt;
    }
}
