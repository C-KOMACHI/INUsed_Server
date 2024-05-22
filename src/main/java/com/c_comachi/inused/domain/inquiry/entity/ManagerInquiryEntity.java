package com.c_comachi.inused.domain.inquiry.entity;

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
@Table(name="manager_inquiry")
public class ManagerInquiryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_inquiry_id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false, nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(cascade  = CascadeType.REMOVE)
    @JoinColumn(name = "inquiry_id")
    private UserInquiryEntity inquiry;

    @Builder
    public ManagerInquiryEntity(Long id, String title, String content, LocalDateTime createdAt, UserInquiryEntity inquiry) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.inquiry = inquiry;
    }
}
