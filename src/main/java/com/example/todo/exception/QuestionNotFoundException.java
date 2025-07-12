package com.example.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "質問が見つかりません")
public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(Long id){
        super("question not found:" + id);
    }
}
