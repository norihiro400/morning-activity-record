package com.example.todo.controller.profile;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.controller.tasks.TaskDTO;
import com.example.todo.service.follow.FollowEntity;
import com.example.todo.service.follow.FollowServiceImpl;
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
    @Autowired
    FollowServiceImpl followService;
    
    // 自分のプロフィール画面
    @GetMapping("")
    public String profile(Model model){
        String username = userService.getusername();
        UserEntity user = userService.findByUsername(username);
        var taskList = taskService.findByuserId(user.getId()).stream().map(TaskDTO::toDTO).toList();
        int taskCount = taskList.size();
        long following = followService.countFollowing(user.getId());
        long followed = followService.countFollowers(user.getId());
        model.addAttribute("taskCount", taskCount);
        model.addAttribute("username", username);
        model.addAttribute("following", following);
        model.addAttribute("followed", followed);
        return "profile/profile";
    }


    // 他人のプロフィール画面
    @GetMapping("/{username}")
    public String otherProfile(@PathVariable String username ,Model model){
        FollowEntity followEntity = createFollowEntity(username);
        boolean isFollowing = followService.isFollowing(followEntity);
        model.addAttribute("isFollowing", isFollowing);
        UserEntity user = userService.findByUsername(username);
        var taskList = taskService.findByuserId(user.getId()).stream().map(TaskDTO::toDTO).toList();
        int taskCount = taskList.size();

        // 表示しているのが自分かどうか
        if(userService.getusername().equals(username)){
            return "redirect:/profile";
        }

        model.addAttribute("username", username);
        model.addAttribute("taskCount", taskCount);
        model.addAttribute("username", username);
        return "profile/other-profile";
    }

    // フォロー機能
    @PostMapping("/follow/{username}")
    public String followUser(@PathVariable String username){
        // フォロー処理の実装
        FollowEntity followEntity = createFollowEntity(username);
        // フォロー処理の呼び出し
        followService.followUser(followEntity);
        String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8);
        return "redirect:/profile/" + encodedUsername;
    }

    // フォロー解除
    @PostMapping("/unfollow/{username}")
    public String unfollowUser(@PathVariable String username){
        FollowEntity followEntity = createFollowEntity(username);
        followService.unfollowUser(followEntity);
        String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8);
        return "redirect:/profile/" + encodedUsername;
    }

    // フォロー中ユーザー表示
    @GetMapping("/following/{username}")
    public String followingUser(@PathVariable String username, Model model){
        UserEntity user = userService.findByUsername(username);
        Long userId = user.getId();
        var followingUserList = followService.getFollowingUser(userId);
        model.addAttribute("following_or_followed", "フォロー中");
        model.addAttribute("usersList", followingUserList);
        return "profile/users";
    }

    // フォロワー表示
    @GetMapping("/followed/{username}")
    public String followedUser(@PathVariable String username, Model model){
        UserEntity user = userService.findByUsername(username);
        Long userId = user.getId();
        var followedUserList = followService.getFollowedUser(userId);
        model.addAttribute("following_or_followed", "フォロワー");
        model.addAttribute("usersList", followedUserList);
        return "profile/users";
    }


    public FollowEntity createFollowEntity(String username) {
        // フォローを行うユーザー
        String followingUsername = userService.getusername();
        UserEntity followingUser = userService.findByUsername(followingUsername);
        Long followingUserId = followingUser.getId();
        // フォローされるユーザー
        UserEntity followedUserEntity = userService.findByUsername(username);
        Long followedUserId = followedUserEntity.getId();
        FollowEntity followEntity = new FollowEntity(null,followingUserId, followedUserId);
        return followEntity;
    }
}
