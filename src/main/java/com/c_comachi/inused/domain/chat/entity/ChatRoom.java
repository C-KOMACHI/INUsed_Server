package com.c_comachi.inused.domain.chat.entity;


import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", nullable = false)
    private Long id;

    @ManyToOne(cascade  = CascadeType.REMOVE)
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne(cascade  = CascadeType.REMOVE)
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    @ManyToOne(cascade  = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;

    @Builder
    public ChatRoom(UserEntity sender, UserEntity receiver, PostEntity post) {
        this.sender = sender;
        this.receiver = receiver;
        this.post = post;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedTime = LocalDateTime.now();
    }
}
