package com.example.todo.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.service.comunity.ComunityEntity;

public interface ComunityRepository extends JpaRepository<ComunityEntity,Long>{
    
    Page<ComunityEntity> findQuestionPage(Pageable pageable);
}
