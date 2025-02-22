package com.example.todo.service.tasks;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task_detail")
public class TaskDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_id")
    private Long taskId;
    @Column(name = "image_path")
    private String imagepath;
    private String detail;

    public TaskDetailEntity(Long id,Long taskId,String imagepath,String detail){
        this.id=id;
        this.taskId = taskId;
        this.imagepath = imagepath;
        this.detail = detail;
    }
    public TaskDetailEntity(){
        
    }
}
