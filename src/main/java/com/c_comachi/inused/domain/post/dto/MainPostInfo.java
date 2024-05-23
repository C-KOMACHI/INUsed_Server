package com.c_comachi.inused.domain.post.dto;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.domain.users.dto.UserInfo;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.global.Time;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
    private UserInfo user;

    private CategoryEntity category;
    private LocalDateTime createdAt;
    private LocalDateTime lastReposting;
    private String ago;
    private boolean checkLiked;
    private boolean checkMyPost;

    public MainPostInfo(PostEntity post, boolean checkLiked, boolean checkMyPost){
        UserEntity user = post.getUser();


        this.Id = post.getId();
        this.title = post.getTitle();
        this.price = post.getPrice();
        this.wishCount = post.getWishCount();
        this.viewCount = post.getViewCount();
        this.imageUrl = post.getImageUrl();
        this.createdAt = post.getCreatedAt();
        this.lastReposting = post.getLastReposting();
        this.productState = post.getProductState();
        this.tag = post.getTag();
        this.category = post.getCategory();
        this.content = post.getContent();
        this.user = new UserInfo(user);
        this.checkLiked = checkLiked;
        this.checkMyPost = checkMyPost;
        this.ago = Time.calculateTime(Date.from(post.getLastReposting().atZone(ZoneId.systemDefault()).toInstant()));

    }
}
