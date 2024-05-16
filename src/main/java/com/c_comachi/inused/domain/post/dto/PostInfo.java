package com.c_comachi.inused.domain.post.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostInfo {
    private MainPostInfo post;
    private boolean checkLiked;

    public PostInfo(MainPostInfo post, boolean checkLiked) {
        this.post = post;
        this.checkLiked = checkLiked;
    }
}
