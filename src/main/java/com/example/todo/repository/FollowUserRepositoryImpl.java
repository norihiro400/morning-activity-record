package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.service.follow.FollowEntity;

@Repository
public interface FollowUserRepositoryImpl extends JpaRepository<FollowEntity, Long> {

    boolean existsByFollowerIdAndFollowedId(Long followingId, Long followedId);

} 