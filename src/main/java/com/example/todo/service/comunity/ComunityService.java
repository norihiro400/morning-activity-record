package com.example.todo.service.comunity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todo.repository.ComunityRepository;

@Service
public class ComunityService {
    @Autowired
    private ComunityRepository comunityRepository;

    //コミュニティの質問投稿
    public void createQuestion(ComunityEntity entity){
        comunityRepository.save(entity);
    }

    //すべての質問の取得
    public List<ComunityEntity> findAllQuestion(){
        return comunityRepository.findAll();
    }
}
