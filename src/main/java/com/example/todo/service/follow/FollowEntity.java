package com.example.todo.service.follow;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "follower_id", nullable = false)
    private final Long followerId; // フォロワーのユーザーID  
    @Column(name = "followed_id", nullable = false)
    private final Long followedId; // フォローされるユーザーID

}
