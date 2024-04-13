package com.c_comachi.inused.domain.inquiry.dto;

import com.c_comachi.inused.domain.inquiry.entity.ManagerInquiryEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ManagerInquiryInfo {
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ManagerInquiryInfo(ManagerInquiryEntity managerInquiry){
        this.title = managerInquiry.getTitle();
        this.content = managerInquiry.getContent();
        this.createdAt = managerInquiry.getCreatedAt();
    }
}
