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

    //完了したミッションをデータベースに保存
    @Transactional
    public void createtask(TaskEntity entity){
        taskRepository.save(entity);
    }
    //データベースからすべての完了済みミッションをとってくる
    public List<TaskEntity> findAll(){
        return taskRepository.findAll();
    }
}
