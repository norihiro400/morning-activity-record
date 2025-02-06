package com.example.todo.controller.tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.todo.controller.getdate.GetDate;
import com.example.todo.service.tasks.TaskService;

@Controller
public class TasksController {

    @Autowired
    TaskService taskService;

    //メイン画面
    @GetMapping("/tasks")
    public String view(Model model){
        var tomorror_task = taskService.findNextTask().stream().map(TaskDTO::toDTO).toList();
        if (tomorror_task.isEmpty()){
            model.addAttribute("task", "予定された朝活はありません");
        }
        //明日の日付を取得
        String tomorror = GetDate.getTomorror();
        model.addAttribute("tomorror", tomorror);
        model.addAttribute("tomorror_task", tomorror_task);
        return "tasks/tasks";
    }

    @PostMapping("/tasks/delete/{id}")
    public String delete(@PathVariable("id") Long taskid){
        taskService.deleteById(taskid);
        return "redirect:/tasks";
    }

    // 朝活の内容を入力、決定する
    @PostMapping("tasks/input")
    public String input_task(@Validated TaskForm form,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/tasks";
        }
        var taskEntity = form.toEntity();
        taskService.createtask(taskEntity);
        return "redirect:/tasks";
    }

    // 完了または未達成のミッションの記録の表示
    @GetMapping("tasks/record")
    public String record(Model model,boolean flag){
        var taskList = taskService.findByDone(flag).stream().map(TaskDTO::toDTO).toList();
        model.addAttribute("tasklist",taskList);
        return "tasks/record";
    }  

    // 詳細画面
    @GetMapping("tasks/{id}")
    public String detail(@PathVariable("id") Long taskid, Model model){
        var task = taskService.findById(taskid).orElseThrow(() -> new RuntimeException("タスクが見つかりません"));
        model.addAttribute("task",task);
        return "tasks/detail";
    };

    //ソート
    @PostMapping("tasks/record/sort")
    public String sort_by_genre(SelectByForm form,Model model){
        var flag = form.isDone();
        return record(model, flag);
    }

    //朝活コミュニティ(悩み相談とか)
    @GetMapping("tasks/comunity")
    public String comunity(){
        return "tasks/comunity";
    }
}

