package com.example.todo.controller.tasks;
import java.io.IOException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.todo.controller.getdate.GetDate;
import com.example.todo.service.login.UserEntity;
import com.example.todo.service.login.UserService;
import com.example.todo.service.tasks.TaskService;

@Controller
public class TasksController {

    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;


    //メイン画面
    @GetMapping("/tasks")
    public String view(Model model, TaskForm form){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userService.findByUsername(username);
        var tomorror_task = taskService.findByDate(LocalDate.now().plusDays(1),user.getId()).stream().map(TaskDTO::toDTO).toList();

        if (form == null){
            form = new TaskForm(null, null);
        }
        model.addAttribute("taskForm",form);

        if (tomorror_task.isEmpty()){
            model.addAttribute("task", "予定された朝活はありません");
        }
        //明日の日付を取得
        String tomorror = GetDate.getTomorror();
        model.addAttribute("tomorror", tomorror);
        model.addAttribute("tomorror_task", tomorror_task);
        model.addAttribute("username", username);
        return "tasks/tasks";
    }
    //指定したidのタスクを削除
    @PostMapping("/tasks/delete/{id}")
    public String delete(@PathVariable("id") Long taskid){
        taskService.deleteById(taskid);
        return "redirect:/tasks";
    }

    // 朝活の内容を入力、決定する
    @PostMapping("tasks/input")
    public String input_task(@Validated TaskForm form,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return view(model,form);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userService.findByUsername(username);
        var taskEntity = form.toEntity(user);
        taskService.createtask(taskEntity);
        return "redirect:/tasks";
    }

    // 完了または未達成のミッションの記録の表示
    @GetMapping("tasks/record")
    public String record(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userService.findByUsername(username);
        var taskList = taskService.findByuserId(user.getId()).stream().map(TaskDTO::toDTO).toList();
        model.addAttribute("tasklist",taskList);
        return "tasks/record";
    }  

    // 詳細画面
    @GetMapping("tasks/{id}")
    public String detail(@PathVariable("id") Long taskid, Model model){
        var task = TaskDTO.toDTO(taskService.findById(taskid).orElseThrow(() -> new RuntimeException("タスクが見つかりません")));
        var detail = TaskDetailDTO.toDTO(taskService.findDetailById(taskid));
        model.addAttribute("task",task);
        model.addAttribute("detail", detail);
        System.out.println(detail.imagepath());
        return "tasks/detail";
    };

    //進行中のタスクを表示
    @GetMapping("/tasks/input")
    public String input(Model model){
        //本日の朝活があればそれを取得(entity->dto)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userService.findByUsername(username);
        var todaytask = taskService.findByDate(LocalDate.now(),user.getId()).stream().map(TaskDTO::toDTO).toList();
        if (todaytask.isEmpty()){
            model.addAttribute("message", "進行中の朝活はありません");
            return "tasks/input";
        }else{      
            model.addAttribute("tasklist", todaytask);
            return "tasks/input";
        }

    }
    //タスクの詳細を記録
    @PostMapping("tasks/detail/{taskId}")
    public String inputdetail(@PathVariable("taskId") Long id, InputForm form){
        // デフォルトパス
        String imagePath = "/images/default.png";
        try{
            MultipartFile imagFile = form.imagepath();
            if (imagFile != null && !imagFile.isEmpty()){
                imagePath = taskService.saveImage(imagFile);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        var entity = form.toDetailEntity(id,imagePath);
        taskService.saveDetail(entity);
        taskService.setidDone(id);
        return "redirect:/tasks/input";
    }
}

