package com.c_comachi.inused.domain.keyword.dto;

import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.global.Time;
import lombok.Getter;

import java.time.ZoneId;
import java.util.Date;

@Getter
public class KeywordPost {
    private final Long postId;
    private final String title;
    private final String imageUrl;
    private final String keyword;
    private final String ago;

    public KeywordPost(PostEntity post, String keyword) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.imageUrl = post.getImageUrl();
        this.keyword = keyword;
        this.ago = Time.calculateTime(Date.from(post.getLastReposting().atZone(ZoneId.systemDefault()).toInstant()));
    }
}


