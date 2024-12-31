package com.example.todo.controller.tasks;

public record TaskForm(
    String task,
    int year,
    int month,
    int date
) {
    
}
