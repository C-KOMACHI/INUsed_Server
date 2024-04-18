package com.c_comachi.inused.domain.post.dto.request;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.domain.post.repository.CategoryRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
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
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Integer price;

    //카테고리 설정 확인 필요. (기본값 설정?)
    private Long categoryId;

    private Status status;

    public CreatePostRequestDto(String title, String content, Integer price, Long categoryId, Status status) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.categoryId = categoryId;
        this.status = status;
    }

    public PostEntity toPost(UserEntity user, CategoryRepository categoryRepository) {
        CategoryEntity category = categoryRepository.findById(categoryId).get();

        return PostEntity.builder()
                .user(user)
                .title(title)
                .content(content)
                .price(price)
                .category(category)
                .productState(Status.ON_SALE)
                .createdAt(LocalDateTime.now())
                //끌올 설정
                .lastReposting(null)
                .wishCount(0)
                .viewCount(0)
                .build();
    }
}
