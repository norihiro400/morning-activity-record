package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.CompletedTaskEntity;

public record TaskForm(
    String task,
    int year,
    int month,
    int date
) {
    public CompletedTaskEntity toEntity(){
        return new CompletedTaskEntity(null,task(),year(),month(),date());
    }
    
}
