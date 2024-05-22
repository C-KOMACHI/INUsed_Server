package com.c_comachi.inused.domain.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="post_image")
public class ImageEntity {
    @Id
    @ManyToOne(cascade  = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    private String image;

    @Builder
    public ImageEntity(PostEntity post, String image) {
        this.post = post;
        this.image = image;
    }
}
