package com.example.todo.service.tasks;
import com.example.todo.controller.tasks.TaskLabel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public TaskEntity(Long id,String task,TaskLabel label,boolean isDone){
        this.id = id;
        this.task = task;
        this.label = label;
        this.isDone = isDone;
    }

    public TaskEntity(){
        
    }
}
