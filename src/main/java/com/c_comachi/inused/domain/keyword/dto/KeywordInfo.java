package com.c_comachi.inused.domain.keyword.dto;

import com.c_comachi.inused.domain.keyword.entity.KeywordEntity;
import com.c_comachi.inused.domain.users.dto.UserInfo;
import lombok.Getter;

@Getter
public class KeywordInfo {
    private Long keywordId;
    private String keyword;
    private UserInfo user;

    public KeywordInfo(KeywordEntity keyword) {
        this.keywordId = keyword.getId();
        this.keyword = keyword.getName();
        this.user = new UserInfo(keyword.getUser());
    }
}
