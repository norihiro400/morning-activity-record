package com.example.todo.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {
    
    //ログイン画面を渡す
    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    //ログインボタンを押したときの処理
    @PostMapping("/login")
    public String post_login() {
        return "redirect:/tasks";
    }
    
}
