package com.c_comachi.inused.domain.review.entity;

import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.report.entity.ReportCategoryEntity;
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
@Table(name="review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer manner;

    @Column(nullable = false)
    private Integer appointment_time;

    @Column(nullable = false)
    private Integer quality;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private UserEntity buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;


    @Builder
    public ReviewEntity(Long id,Integer manner,Integer appointment_time,Integer quality,
                        PostEntity post,UserEntity buyer,UserEntity seller) {
        this.id = id;
        this.manner = manner;
        this.appointment_time = appointment_time;
        this.quality = quality;
        this.post = post;
        this.buyer = buyer;
        this.seller = seller;
    }
}
