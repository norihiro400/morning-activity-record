package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {
    
    // 最初の画面(ログインページとか)
    @GetMapping("/")
    public String view(){
        return "index";
    }
    @PostMapping("/")
    public String login(){
        return "redirect:/tasks";
    }
}
