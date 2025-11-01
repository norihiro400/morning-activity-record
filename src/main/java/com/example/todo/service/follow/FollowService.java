package com.example.todo.service.follow;

import java.util.List;

public interface FollowService {
    public void followUser(FollowEntity entity);
    public boolean isFollowing(FollowEntity entity);
    public List<Long> getFollowingUserId(Long userid);
    public List<Long> getFollowedUserId(Long userId);
    public void unfollowUser(FollowEntity entity);
    // public void unfollowUser(Long followerId, Long followedId);
}
