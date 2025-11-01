package com.example.todo.service.follow;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.todo.repository.FollowUserRepositoryImpl;
import com.example.todo.service.login.UserService;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowUserRepositoryImpl followUserRepositoryImpl;
    private final UserService userService;

    public FollowServiceImpl(FollowUserRepositoryImpl followUserRepositoryImpl, UserService userService) {
        this.followUserRepositoryImpl = followUserRepositoryImpl;
        this.userService = userService;
    }
    @Override
    public void followUser(FollowEntity entity){
        // フォロー処理の実装
        followUserRepositoryImpl.save(entity);
    }

    @Override
    public void unfollowUser(FollowEntity entity){
        followUserRepositoryImpl.deleteByFollowerIdAndFollowedId(entity.getFollowerId(),entity.getFollowedId());
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
        return followingUserIdList; 
    }

   public List<String> getFollowingUser(Long userId){
        List<String> followingUserList = followUserRepositoryImpl.findAllByFollowerId(userId).
                                        stream().
                                        map(FollowEntity::getFollowedId).
                                        map(id -> userService.getUserNameById(id)).
                                        toList();        
        return followingUserList; 
    }

    // フォロワーのユーザー一覧取得
    @Override
    public List<Long> getFollowedUserId(Long userid){
        List<Long> followedUserIdList = followUserRepositoryImpl.findAllByFollowedId(userid).
                                        stream().
                                        map(FollowEntity::getFollowerId).
                                        toList();
        return followedUserIdList;
    }

    public List<String> getFollowedUser(Long userId){
        List<String> followedUserList = followUserRepositoryImpl.findAllByFollowedId(userId).
                                        stream().
                                        map(FollowEntity::getFollowerId).
                                        map(id -> userService.getUserNameById(id)).
                                        toList();
        return followedUserList;
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
