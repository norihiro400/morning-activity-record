package com.example.todo.controller.tasks;
import org.springframework.web.multipart.MultipartFile;
import com.example.todo.service.tasks.TaskDetailEntity;
import com.example.todo.service.tasks.TaskEntity;

import jakarta.validation.constraints.NotBlank;

public record InputForm(
    @NotBlank(message = "詳細を入力してください")
    String detail,
    MultipartFile imagepath,
    Boolean isPublic
) {
    public TaskDetailEntity toDetailEntity(TaskEntity task,String path){
        return new TaskDetailEntity(null,task,path,detail(),isPublic());
    }
}
