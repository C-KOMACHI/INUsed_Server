package com.c_comachi.inused.domain.inquiry.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInquiryEditInfo {
    String title;
    String content;

    @Builder
    public UserInquiryEditInfo(String title, String content){
        this.title = title;
        this.content = content;
    }
}
