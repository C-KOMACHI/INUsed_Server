package com.c_comachi.inused.domain.review.dto.request;

import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.review.entity.ReviewEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateReviewRequestDto {

    private final Integer manner;

    private final Integer appointment_time;

    private final Integer quality;

    private final Long postId;

    private final Long receiverId;


    public CreateReviewRequestDto(Integer manner, Integer appointment_time, Integer quality, Long postId, Long receiverId) {
        this.manner = manner;
        this.appointment_time = appointment_time;
        this.quality = quality;
        this.postId = postId;
        this.receiverId = receiverId;

    }

    public ReviewEntity toReviewEntity(PostEntity post, UserEntity receiver,UserEntity sender) {

        return ReviewEntity.builder()
                    .manner(this.manner)
                    .appointment_time(this.appointment_time)
                    .quality(this.quality)
                    .post(post)
                    .sender(sender)
                    .receiver(receiver)
                    .build();

    }

}
