package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    
    // 最初の画面(ログインページとか)
    @GetMapping("/")
    public String view(){
        return "index";
    }
    
}
