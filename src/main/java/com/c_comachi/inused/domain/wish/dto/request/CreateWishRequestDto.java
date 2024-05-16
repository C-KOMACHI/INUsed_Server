package com.c_comachi.inused.domain.wish.dto.request;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.repository.CategoryRepository;
import com.c_comachi.inused.domain.post.repository.PostRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.wish.entity.WishEntity;
import com.c_comachi.inused.domain.wish.repository.WishRepository;
import lombok.Data;

@Data
public class CreateWishRequestDto {
    private Long postId;

    public CreateWishRequestDto(Long postId) {
        this.postId = postId;
    }

    public WishEntity toWish(UserEntity user, PostRepository postRepository) {
        PostEntity post = postRepository.findById(postId).get();
                //.orElseThrow(() -> new EntityNotFoundException("Post not found with ID: " + postId));

        return WishEntity.builder()
                .user(user)
                .post(post)
                .build();
    }
}