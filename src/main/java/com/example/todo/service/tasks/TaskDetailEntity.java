package com.example.todo.service.tasks;
import java.util.List;

import com.example.todo.controller.tasks.TaskDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task_detail")
public class TaskDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false, unique = true)
    private TaskEntity task;
    @Column(name = "image_path")
    private String imagepath;
    private String detail;

    @Column(name = "is_public")
    private Boolean isPublic;

    public TaskDetailEntity(Long id,TaskEntity task,String imagepath,String detail,boolean isPublic){
        this.id=id;
        this.task = task;
        this.imagepath = imagepath;
        this.detail = detail;
        this.isPublic = isPublic;
    }
    public TaskDetailEntity(){
        
    }
}
