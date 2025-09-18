package com.example.todo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.service.follow.FollowEntity;

@Repository
public interface FollowUserRepositoryImpl extends JpaRepository<FollowEntity, Long> {

    boolean existsByFollowerIdAndFollowedId(Long followingId, Long followedId);

    List<FollowEntity> findAllByFollowerId(Long userId);

    List<FollowEntity> findAllByFollowedId(Long userid);
    
    long countByFollowerId(Long userId);   // フォロー中の人数
    long countByFollowedId(Long userId);   // フォロワーの人数

} 