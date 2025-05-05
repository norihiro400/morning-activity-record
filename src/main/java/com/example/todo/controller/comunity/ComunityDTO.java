package com.example.todo.controller.comunity;

import com.example.todo.service.comunity.ComunityEntity;

public record ComunityDTO(
    Long id,
    String title,
    String content
) {
    public static ComunityDTO toDTO(ComunityEntity entity){
        return new ComunityDTO(entity.getId(),entity.getTitle(),entity.getContent());
    }
}
