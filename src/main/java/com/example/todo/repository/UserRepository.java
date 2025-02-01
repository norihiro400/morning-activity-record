package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.service.login.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    UserEntity findByUsername(String username);
}
