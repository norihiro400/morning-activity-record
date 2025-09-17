package com.example.todo.service.follow;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class FollowEntity {
    private final Long id;
    private final Long followerId; // フォロワーのユーザーID  
    private final Long followedId; // フォローされるユーザーID

}
