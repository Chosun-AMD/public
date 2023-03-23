package com.example.amd_project.Domain.User.Repository;

import com.example.amd_project.Domain.User.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}
