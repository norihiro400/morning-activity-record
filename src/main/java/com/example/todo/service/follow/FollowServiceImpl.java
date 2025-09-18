package com.example.todo.service.follow;

import org.springframework.stereotype.Service;

import com.example.todo.repository.FollowUserRepositoryImpl;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowUserRepositoryImpl followUserRepositoryImpl;

    public FollowServiceImpl(FollowUserRepositoryImpl followUserRepositoryImpl) {
        this.followUserRepositoryImpl = followUserRepositoryImpl;
    }
    @Override
    public void followUser(FollowEntity entity){
        // フォロー処理の実装
        followUserRepositoryImpl.save(entity);
    }

    // フォロー済みかどうかの確認
    public boolean isFollowing(FollowEntity entity){
        return followUserRepositoryImpl.existsByFollowerIdAndFollowedId(entity.getFollowerId(), entity.getFollowedId());
    }
    
}
