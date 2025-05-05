package com.example.todo.controller.comunity;

import com.example.todo.service.comunity.AnswerEntity;

public record AnswerDTO(
    Long id,
    String content,
    Long questionId
) {
    public static AnswerDTO toDTO(AnswerEntity entity){
        return new AnswerDTO(entity.getId(),entity.getContent(),entity.getQuestionId());
    }
}
