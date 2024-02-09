package com.c_comachi.inused.domain.post.entity;

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
@Table(name="posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "wish_count",nullable = false)
    private Integer wishCount;

    @Column(name = "view_count",nullable = false)
    private Integer viewCount;

    @Column(name = "product_state",nullable = false)
    private Status productState;

    @CreatedDate
    @Column(updatable = false, nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "last_reposting")
    private LocalDateTime lastReposting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Builder
    public PostEntity(Long id, String title, String content, Integer price, Integer wishCount,
                      Integer viewCount, Status productState, LocalDateTime createdAt, LocalDateTime updatedAt,
                      LocalDateTime lastReposting, UserEntity user, CategoryEntity category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.wishCount = wishCount;
        this.viewCount = viewCount;
        this.productState = productState;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastReposting = lastReposting;
        this.user = user;
        this.category = category;
    }
}
