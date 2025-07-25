package com.example.todo.service.tasks;
import java.time.LocalDate;
import com.example.todo.controller.tasks.TaskLabel;
import com.example.todo.service.login.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task;

    @Enumerated(EnumType.STRING) 
    private TaskLabel label;

    private boolean isDone;
    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public TaskEntity(Long id,String task,TaskLabel label,boolean isDone,LocalDate scheduledDate,UserEntity user){
        this.id = id;
        this.task = task;
        this.label = label;
        this.isDone = isDone;
        this.scheduledDate = scheduledDate;
        this.user = user;
    }

    public TaskEntity(){
        
    }
}
