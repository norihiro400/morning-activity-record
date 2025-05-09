package com.example.todo.controller.comunity;

import com.example.todo.service.comunity.ComunityEntity;
import jakarta.validation.constraints.NotBlank;

public record ComunityForm(
    @NotBlank(message = "タイトルを入力してください")
    String title,
    @NotBlank(message = "内容を入力してください")
    String content
) {
    public ComunityEntity toEntity(){
        return new ComunityEntity(null,title,content);
    }
}

