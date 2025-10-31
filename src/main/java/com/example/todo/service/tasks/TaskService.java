package com.example.todo.service.tasks;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.todo.repository.TaskDetailRepository;
import com.example.todo.repository.TaskRepository;
import jakarta.transaction.Transactional;

@Service
public class TaskService {
    private static final String UPLOAD_DIR = "images/";

    private final TaskRepository taskRepository;
    private final TaskDetailRepository taskDetailRepository;

    public TaskService(TaskRepository taskRepository,TaskDetailRepository taskDetailRepository){
        this.taskRepository = taskRepository;
        this.taskDetailRepository = taskDetailRepository;
    }

    // タスク作成
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

    // ページネーション対応版
    public Page<TaskEntity> findByUserIdPaged(Long userId , int page , int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("scheduledDate").descending());
        return taskRepository.findByUserIdAndIsDoneTrue(userId, pageable);
    }

    //指定した日づけの朝活を取得(自分のユーザーIDのもののみ取得)かつisDoneがfalse(未達成)のもの
    public List<TaskEntity> findByDate(LocalDate localDate,Long userId){
        return taskRepository.findByScheduledDateAndUserIdAndIsDone(localDate,userId);
    }
    //指定したidのタスクを削除
    public void deleteById(Long taskid){
        taskRepository.deleteById(taskid);
    }
    //タスクの詳細を保存
    public void saveDetail(TaskDetailEntity entity){
        taskDetailRepository.save(entity);
    }

    // 完了したタスクのステータスをtrueに
    public void setidDone(Long taskId){
        taskRepository.setisDoneById(taskId);
    }
    //指定したtaskIdのタスクの詳細を取得
    public TaskDetailEntity findDetailById(Long id){
        return taskDetailRepository.findByTaskId(id);
    }
    //画像のアップロード処理
    public String saveImage(MultipartFile image) throws IOException{
        if (image.isEmpty()){
            return null;
        }
        String filename = System.currentTimeMillis() + '_' + image.getOriginalFilename();
        Path filepath = Paths.get(UPLOAD_DIR + filename);
        System.out.println(filename); 
        System.out.println(filepath); 
        Files.copy(image.getInputStream(),filepath);
        return "/images/" + filename;
    }

    //今日の朝活を取得
    public List<TaskEntity> findTodayTask(LocalDate localDate){
        return taskRepository.findByScheduledDate(localDate);
    }

    //公開済みの朝活の取得
    public List<TaskDetailEntity> getPublicTasks(){
        return taskDetailRepository.findAllByisPublicTrue();
    }
}


