package com.c_comachi.inused.domain.wish.entity;

import com.c_comachi.inused.domain.users.entity.Authority;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="wish")
public class WishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private UserEntity post;

    @Builder
    public WishEntity(Long id, UserEntity user, UserEntity post){
        this.id = id;
        this.user = user;
        this.post = post;
    }
}
