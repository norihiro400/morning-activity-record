package com.example.todo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.todo.service.tasks.TaskEntity;

import jakarta.transaction.Transactional;

import java.util.List;
import java.time.LocalDate;



@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    List<TaskEntity> findByIsDone(boolean isDone);

    List<TaskEntity> findByScheduledDate(LocalDate scheduled_date);

    List<TaskEntity> findByUserId(Long user_id);

    @Query(value = "select * from task where scheduled_date = :date and user_id = :userId and is_Done = false",nativeQuery = true)
    List<TaskEntity> findByScheduledDateAndUserIdAndIsDone(@Param("date") LocalDate date,@Param("userId") Long userId);
    
    @Query(value = "select * from task where user_id = :userId and is_Done = true",nativeQuery = true)
    List<TaskEntity> findByUserIdAndTrue(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("update TaskEntity t set t.isDone = true where t.id = :taskId")
    void setisDoneById(@Param("taskId") Long taskId);

    //今日のみんなの朝活を取得
    @Query(value = "select * from task where scheduled_date = :date and is_Done = false",nativeQuery = true)
    List<TaskEntity> findByScheduledDateAndIsDone(@Param("date") LocalDate date);
}
