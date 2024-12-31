package com.example.todo.service.tasks;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task")
public class CompletedTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;
    private int year;
    private int month;
    private int date;

    public CompletedTaskEntity(Long id,String task,int year,int month,int date){
        this.id = id;
        this.task = task;
        this.year = year;
        this.month = month;
        this.date = date;
    }

}
