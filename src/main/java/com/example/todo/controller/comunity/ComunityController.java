package com.example.todo.controller.comunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.todo.service.tasks.TaskService;

@Controller
public class ComunityController {
    @Autowired
    TaskService taskService;
    

    //コミュニティ画面
    @GetMapping("/comunity")
    public String comunity(Model model) {
        return "comunity/comunity";
    }

    @GetMapping("/comunity/{questionId}")
    public String questionDetail(@PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("Id", questionId);
        return "comunity/detail";
    }
}
