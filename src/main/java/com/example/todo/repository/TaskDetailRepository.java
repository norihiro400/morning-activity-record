package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.service.tasks.TaskDetailEntity;

public interface TaskDetailRepository extends JpaRepository<TaskDetailEntity,Long>{
    
}
