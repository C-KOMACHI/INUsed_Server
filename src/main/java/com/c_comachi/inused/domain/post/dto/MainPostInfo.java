package com.c_comachi.inused.domain.post.dto;

import com.c_comachi.inused.domain.post.entity.PostEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MainPostInfo {
    private String title;
    private Integer price;
    private Integer wishCount;
    private Integer viewCount;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastReposting;

    public MainPostInfo(PostEntity post){
        this.title = post.getTitle();
        this.price = post.getPrice();
        this.wishCount = post.getWishCount();
        this.viewCount = post.getViewCount();
        this.imageUrl = post.getImageUrl();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.lastReposting = post.getLastReposting();
    }
}
