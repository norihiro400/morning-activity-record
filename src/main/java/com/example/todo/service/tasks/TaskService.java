package com.example.todo.service.tasks;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todo.controller.tasks.TaskDTO;
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

    //指定したidのものを取得
    public Optional<TaskEntity> findById(Long taskid){
        return taskRepository.findById(taskid);
    }
    //達成済みのものを取得
    public List<TaskEntity> findByDone(boolean isDone){
        return taskRepository.findByIsDone(isDone);
    }
}
