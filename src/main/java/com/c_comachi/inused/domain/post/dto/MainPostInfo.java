package com.c_comachi.inused.domain.post.dto;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MainPostInfo {
    private Long Id;
    private String title;
    private Integer price;
    private String content;
    private Status productState;
    private Integer wishCount;
    private Integer viewCount;
    private String imageUrl;
    private String tag;
    private String nickname;
    private String profileImage;

    private CategoryEntity category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastReposting;

    public MainPostInfo(PostEntity post){
        this.Id = post.getId();
        this.title = post.getTitle();
        this.price = post.getPrice();
        this.wishCount = post.getWishCount();
        this.viewCount = post.getViewCount();
        this.imageUrl = post.getImageUrl();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.lastReposting = post.getLastReposting();
        this.productState = post.getProductState();
        this.tag = post.getTag();
        this.category = post.getCategory();
        this.content = post.getContent();
        this.nickname = post.getUser().getNickname();
        this.profileImage = post.getUser().getProfileImage();
    }
}
