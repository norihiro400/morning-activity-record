package com.example.todo.service.follow;

import java.util.List;

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
    @Override
    public boolean isFollowing(FollowEntity entity){
        return followUserRepositoryImpl.existsByFollowerIdAndFollowedId(entity.getFollowerId(), entity.getFollowedId());
    }

    // フォロー中のユーザー一覧取得
    @Override
    public List<Long> getFollowingUserId(Long userId){
        List<Long> followingUserIdList = followUserRepositoryImpl.findAllByFollowerId(userId).
                                        stream().
                                        map(FollowEntity::getFollowedId).
                                        toList();        
        return followingUserIdList; // 仮の実装
    }

    // フォロワーのユーザー一覧取得
    @Override
    public List<Long> getFollowedUserId(Long userid){
        List<Long> followedUserIdList = followUserRepositoryImpl.findAllByFollowedId(userid).
                                        stream().
                                        map(FollowEntity::getFollowerId).
                                        toList();
        return followedUserIdList; // 仮の実装
    }

    // フォロー中の人数をカウント
    public long countFollowing(Long userId) {
        return followUserRepositoryImpl.countByFollowerId(userId);
    }
    // フォロワーの人数をカウント
    public long countFollowers(Long userId) {
        return followUserRepositoryImpl.countByFollowedId(userId);
    }
    
}
