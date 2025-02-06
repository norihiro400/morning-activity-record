package com.example.todo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.todo.service.tasks.TaskEntity;
import java.util.List;
import java.time.LocalDate;



@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    List<TaskEntity> findByIsDone(boolean isDone);

    List<TaskEntity> findByScheduledDate(LocalDate scheduled_date);
}
