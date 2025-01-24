package com.example.todo.controller.tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.service.tasks.TaskEntity;
import com.example.todo.service.tasks.TaskService;
import jakarta.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;


@Controller
public class TasksController {

    @Autowired
    TaskService taskService;
    @Autowired
    HttpSession session;

    //メイン画面
    @GetMapping("/tasks")
    public String view(Model model){
        String task = (String)session.getAttribute("task");
        if (task != null){
            model.addAttribute("task", task);
        }
        //今日の日にちをviewに渡す
        Calendar calendar = Calendar.getInstance();
        model.addAttribute("year",calendar.get(Calendar.YEAR));
        model.addAttribute("month", calendar.get(Calendar.MONTH)+1);
        model.addAttribute("date", calendar.get(Calendar.DATE));
        return "tasks/tasks";
    }
    // 朝活の内容を入力、決定する
    @PostMapping("tasks/input")
    public String input_task(TaskForm form){
        var taskEntity = form.toEntity();
        taskService.createtask(taskEntity);
        return "redirect:/tasks";
    }

    // 完了したミッションの記録の表示
    @GetMapping("tasks/record")
    public String record(){
        // List<CompletedTaskEntity> entity;
        // entity = taskService.findAll();
        // model.addAttribute("cmptasklist",entity);
        return "tasks/record";
    }  
}
