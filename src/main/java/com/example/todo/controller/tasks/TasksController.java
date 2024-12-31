package com.example.todo.controller.tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.service.tasks.CompletedTaskEntity;
import com.example.todo.service.tasks.TaskService;
import jakarta.servlet.http.HttpSession;
import java.util.Calendar;


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
    //ミッションを選択する
    @PostMapping("tasks/select")
    public String select_task(){
        var task = taskService.find();
        session.setAttribute("task", task);
        return "redirect:/tasks";
    }

    //完了したミッションの記録の表示
    @GetMapping("tasks/record")
    public String record(Model model){
        model.addAttribute("key", "value");
        return "tasks/record";
    }

    @PostMapping("tasks/complete")
    public String complete(TaskForm form){
        CompletedTaskEntity entity = new CompletedTaskEntity(null, form.task(), form.year(), form.month(), form.date());
        taskService.cmptask(entity);
        return "redirect:/tasks";
    }
    
}
