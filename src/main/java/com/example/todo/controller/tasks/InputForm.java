package com.example.todo.controller.tasks;

import org.springframework.web.multipart.MultipartFile;

import com.example.todo.service.tasks.TaskDetailEntity;

public record InputForm(
    String detail,
    MultipartFile image_path
) {
    public TaskDetailEntity taskDetailEntity(Long taskId){
        return new TaskDetailEntity(null,taskId,"/image/",detail());
    }
}
