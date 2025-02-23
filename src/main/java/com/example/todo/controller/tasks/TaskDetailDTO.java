package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskDetailEntity;

public record TaskDetailDTO(
    Long id,
    String imagepath,
    String detail

) {
    public static TaskDetailDTO toDTO(TaskDetailEntity entity){
        return new TaskDetailDTO(entity.getId(), entity.getImagepath(), entity.getDetail());
    }
}
