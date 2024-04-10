package com.c_comachi.inused.domain.inquiry.entity;

import com.c_comachi.inused.domain.inquiry.dto.UserInquiryEditInfo;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.report.entity.ReportCategoryEntity;
import com.c_comachi.inused.domain.users.dto.UserEditInfo;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="user_inquiry")
public class UserInquiryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false, nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public UserInquiryEntity(String title, String content, LocalDateTime createdAt, UserEntity user) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }

    public void userInquiryEdit (UserInquiryEditInfo info){
        this.title = info.getTitle();
        this.content = info.getContent();
    }
}
