package com.example.todo.controller.profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.todo.service.login.UserService;


@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    UserService userService;
    
    @GetMapping("")
    public String profile(Model model){
        String username = userService.getusername();
        model.addAttribute(username, username);
        return "profile/profile";
    }
}
