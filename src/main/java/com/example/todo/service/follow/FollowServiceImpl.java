package com.example.todo.service.follow;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void unfollowUser(FollowEntity entity){
        boolean exists = followUserRepositoryImpl.existsByFollowerIdAndFollowedId(
            entity.getFollowerId(),
            entity.getFollowedId()
        );

        if (!exists) {
            System.out.println("[WARN] 該当するフォロー関係が存在しません。followerId=" 
                + entity.getFollowerId() + ", followedId=" + entity.getFollowedId());
            // ここでreturnしてスルーでもいいし、例外投げてもいい
            return;
        }

        try {
            followUserRepositoryImpl.deleteByFollowerIdAndFollowedId(
                entity.getFollowerId(),
                entity.getFollowedId()
            );
            System.out.println("[INFO] フォロー解除成功: followerId=" 
                + entity.getFollowerId() + ", followedId=" + entity.getFollowedId());
        } catch (Exception e) {
            System.err.println("[ERROR] フォロー解除中にエラー発生: " + e.getMessage());
            throw e; // 上層でハンドリングしたいならここで再throw
        }
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
