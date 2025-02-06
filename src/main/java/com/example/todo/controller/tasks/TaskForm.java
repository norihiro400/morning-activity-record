package com.example.todo.controller.tasks;
import com.example.todo.service.tasks.TaskEntity;
import jakarta.validation.constraints.NotBlank;

public record TaskForm(
    @NotBlank
    String task,
    
    String label
) {
    public TaskEntity toEntity(){
        return new TaskEntity(null,task,TaskLabel.valueOf(label),false);
    }
    
}
