package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.service.tasks.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
}
