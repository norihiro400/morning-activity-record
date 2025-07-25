package com.example.todo.repository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.service.tasks.TaskDetailEntity;
import java.util.List;


public interface TaskDetailRepository extends JpaRepository<TaskDetailEntity,Long>{

    TaskDetailEntity findByTaskId(Long taskId);

    @Query("SELECT t FROM TaskDetailEntity t WHERE t.isPublic = true")
    List<TaskDetailEntity> findAllByisPublicTrue();
}
