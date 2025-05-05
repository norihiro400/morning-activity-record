package com.example.todo.controller.comunity;

import com.example.todo.service.comunity.ComunityEntity;

public record ComunityForm(
    String title,
    String content
) {
    public ComunityEntity toEntity(){
        return new ComunityEntity(null,title,content);
    }
}

