package com.example.todo.service.comunity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.todo.repository.AnswerRepository;
import com.example.todo.repository.ComunityRepository;


@Service
public class ComunityService {
    @Autowired
    private ComunityRepository comunityRepository;
    @Autowired
    private AnswerRepository answerRepository;

    //コミュニティの質問投稿
    public void createQuestion(ComunityEntity entity){
        comunityRepository.save(entity);
    }

    // idを指定して質問を取得
    public Optional<ComunityEntity> findById(Long id){
        return comunityRepository.findById(id);
    }

    //すべての質問の取得
    public List<ComunityEntity> findAllQuestion(){
        return comunityRepository.findAll();
    }

    //質問取得　ページネーション対応
    public Page<ComunityEntity> findQuestionPage(int page , int size){
        Pageable pageable = PageRequest.of(page, size);
        return comunityRepository.findQuestionPage(pageable);
    }

    //質問の回答を投稿
    public void createAnswer(AnswerEntity entity){
        //質問の回答を保存
        answerRepository.save(entity);
    }

    //質問の回答を取得
    public List<AnswerEntity> findAnswerbyId(Long questionId){
        return answerRepository.findByQuestionId(questionId);
    }


}
