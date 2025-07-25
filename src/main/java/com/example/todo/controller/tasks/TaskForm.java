package com.example.todo.controller.tasks;
import java.time.LocalDate;
import com.example.todo.service.login.UserEntity;
import com.example.todo.service.tasks.TaskEntity;
import jakarta.validation.constraints.NotBlank;

public record TaskForm(
    @NotBlank(message = "内容を入力してください")
    String task,
    
    String label
) {

    public TaskEntity toEntity(UserEntity userentity){
        return new TaskEntity(null,task,TaskLabel.valueOf(label),false, LocalDate.now().plusDays(1),userentity); 
    }
    
}
