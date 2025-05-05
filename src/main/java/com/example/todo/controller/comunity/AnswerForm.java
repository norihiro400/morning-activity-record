package com.example.todo.controller.comunity;

import com.example.todo.service.comunity.AnswerEntity;

public record AnswerForm(
    String content
) {
    public AnswerEntity toEntity(Long questionId){
        return new AnswerEntity(null,content,questionId);
    }
    
}
