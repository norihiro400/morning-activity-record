package com.example.todo.controller.comunity;

import com.example.todo.service.comunity.AnswerEntity;
import jakarta.validation.constraints.NotBlank;
public record AnswerForm(
    @NotBlank(message = "内容を入力してください")
    String content
) {
    public AnswerEntity toEntity(Long questionId){
        return new AnswerEntity(null,content,questionId);
    }
}
