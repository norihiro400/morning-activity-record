package com.example.todo.service.tasks;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.repository.TaskDetailRepository;
import com.example.todo.repository.TaskRepository;
import jakarta.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired 
    TaskDetailRepository taskDetailRepository;

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
    //flagによって取得
    public List<TaskEntity> findByDone(boolean isDone){
        return taskRepository.findByIsDone(isDone);
    }
    //useridでとってくるかつ達成済み
    public List<TaskEntity> findByuserId(Long id){
        return taskRepository.findByUserIdAndTrue(id);
    }
    //指定した日づけの朝活を取得(自分のユーザーIDのもののみ取得)
    public List<TaskEntity> findByDate(LocalDate localDate,Long userId){
        return taskRepository.findByScheduledDateAndUserId(localDate,userId);
    }
    //指定したidのタスクを削除
    public void deleteById(Long taskid){
        taskRepository.deleteById(taskid);
    }
    //タスクの詳細を保存
    public void saveDetail(TaskDetailEntity entity){
        taskDetailRepository.save(entity);
    }

}
