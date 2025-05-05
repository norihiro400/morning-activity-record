package com.example.todo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.service.comunity.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    List<AnswerEntity> findByQuestionId(Long questionId);   
}
