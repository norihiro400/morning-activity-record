package com.example.todo.controller.profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.controller.tasks.TaskDTO;
import com.example.todo.service.login.UserEntity;
import com.example.todo.service.login.UserService;
import com.example.todo.service.tasks.TaskService;


@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    
    @GetMapping("")
    public String profile(Model model){
        String username = userService.getusername();
        UserEntity user = userService.findByUsername(username);
        var taskList = taskService.findByuserId(user.getId()).stream().map(TaskDTO::toDTO).toList();
        int taskCount = taskList.size();
        model.addAttribute("taskCount", taskCount);
        model.addAttribute(username, username);
        return "profile/profile";
    }
}
