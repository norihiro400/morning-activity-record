package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskEntity;

public record TaskDTO(
    Long id,
    String task,
    String label,
    boolean isDone
) {
    public static TaskDTO toDTO(TaskEntity entity){
        return new TaskDTO(entity.getId(), entity.getTask(), entity.getLabel().name(), entity.isDone());
    }
} 


