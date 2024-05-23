package com.c_comachi.inused.domain.post.dto.request;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.domain.post.repository.CategoryRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.global.exception.EntityNotFoundException;
import com.c_comachi.inused.global.common.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostRequestDto {
    private String title;

    private String content;

    private String imageUrl;

    private String tag;

    private Integer price;

    //카테고리 설정 확인 필요. (기본값 설정?)
    private Long categoryId;


    public CreatePostRequestDto(String title, String content, Integer price, Long categoryId, String imageUrl, String tag) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.tag = tag;
    }

    public PostEntity toPost(UserEntity user, CategoryRepository categoryRepository) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        return PostEntity.builder()
                .user(user)
                .title(title)
                .content(content)
                .price(price)
                .category(category)
                .productState(Status.ON_SALE)
                .createdAt(LocalDateTime.now())
                .imageUrl(imageUrl)
                .tag(tag)
                .lastReposting(LocalDateTime.now())
                //끌올 설정
                .wishCount(0)
                .viewCount(0)
                .build();
    }
}
