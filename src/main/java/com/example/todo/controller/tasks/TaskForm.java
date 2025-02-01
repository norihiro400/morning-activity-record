package com.example.todo.controller.tasks;

import com.example.todo.service.tasks.TaskEntity;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

public record TaskForm(
    @NotNull
    String task,

    String label
) {
    public TaskEntity toEntity(){
        return new TaskEntity(null,task,TaskLabel.valueOf(label),false);
    }
    
}
