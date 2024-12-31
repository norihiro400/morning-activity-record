package com.example.todo.service.tasks;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todo.controller.tasks.TasksDTO;
import com.example.todo.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    
    //ランダムにタスクを決定する処理
    public String find(){
        var task1 = new TasksDTO("ランニングをしよう！");
        var task2 = new TasksDTO("思い切って外食しよう！");
        var task3 = new TasksDTO("近所を散歩しよう");

        var taskList = List.of(task1,task2,task3);
        Random rand = new Random();
        int num = rand.nextInt(3);

        return taskList.get(num).task();
    }
    //完了したミッションをデータベースに保存
    @Transactional
    public void cmptask(CompletedTaskEntity entity){
        taskRepository.save(entity);
    }

    //データベースからすべての完了済みミッションをとってくる
    public List<CompletedTaskEntity> findAll(){
        return taskRepository.findAll();
    }
}
