package com.c_comachi.inused.domain.keyword.dto;

import lombok.Getter;

@Getter
public class KeywordPost {
    private String title;
    private String imageUrl;
    private String ago;
    private String type;

    public KeywordPost(String title, String imageUrl, String ago) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.ago = ago;
    }
}


