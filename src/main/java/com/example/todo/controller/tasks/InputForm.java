package com.example.todo.controller.tasks;

import org.springframework.web.multipart.MultipartFile;

import com.example.todo.service.tasks.TaskDetailEntity;

public record InputForm(
    String detail,
    MultipartFile imagepath
) {
    public TaskDetailEntity toDetailEntity(Long taskId,String path){
        return new TaskDetailEntity(null,taskId,path,detail());
    }
}
