package com.c_comachi.inused.domain.post.dto;

import lombok.Data;

@Data
public class PostInfo {
    private MainPostInfo post;
    private boolean checkLiked;
}
