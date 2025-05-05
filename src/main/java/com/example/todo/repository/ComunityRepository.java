package com.example.todo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.service.comunity.ComunityEntity;

public interface ComunityRepository extends JpaRepository<ComunityEntity,Long>{
    
}
