package com.example.todo.controller.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.todo.service.login.UserEntity;
import com.example.todo.service.login.UserService;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    //ログイン機能
    @GetMapping("/login")
    public String loginpage(){
        return "login/login";
    }
    @PostMapping("/login")
    public String login(){
        return "redirect:/tasks";
    }

    @GetMapping("/")
    public String index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()){
            return "redirect:/tasks";
        }
        return "redirect:/login";
    }


    //新規登録機能
    @GetMapping("/register")
    public ModelAndView registerform(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", new UserDTO());
        mav.setViewName("login/register");
        return mav;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO){
        UserEntity existing = userService.findByUsername(userDTO.getUsername());
        if(existing != null){
            return "login/register";
        }
        userService.save(userDTO);
        return "redirect:/login";
    }
    
}
