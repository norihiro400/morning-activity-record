package com.example.todo.controller.tasks;
import java.time.LocalDate;

import com.example.todo.service.tasks.TaskDetailEntity;
import com.example.todo.service.tasks.TaskEntity;

public record TaskDTO(
    Long id,
    String task,
    String label,
    boolean isDone,
    LocalDate scheduledDate,
    String username
) {
    public static TaskDTO toDTO(TaskEntity entity){
        return new TaskDTO(entity.getId(), entity.getTask(), entity.getLabel().name(), entity.isDone(),entity.getScheduledDate(),entity.getUser().getUsername());
    }


    public static TaskDTO fromTaskDetailEntity(TaskDetailEntity taskDetailEntity){
        TaskEntity entity = taskDetailEntity.getTask();
        TaskDTO dto= new TaskDTO(entity.getId(), entity.getTask(), entity.getLabel().name(), entity.isDone(),entity.getScheduledDate(),entity.getUser().getUsername());
        return dto;
    }
} 


