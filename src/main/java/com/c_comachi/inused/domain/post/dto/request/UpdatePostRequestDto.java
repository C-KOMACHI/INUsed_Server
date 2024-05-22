package com.c_comachi.inused.domain.post.dto.request;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.domain.post.repository.CategoryRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePostRequestDto {

    private String title;

    private String content;

    private String imageUrl;

    private Integer price;

    //카테고리 설정 확인 필요. (기본값 설정?)
    private  Long categoryId;

    private Status productState;

    public UpdatePostRequestDto(String title, String content, Integer price, Long categoryId, Status productState, String imageUrl) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.categoryId = categoryId;
        this.productState = productState;
        this.imageUrl = imageUrl;
    }

    public PostEntity updatePost(CategoryRepository categoryRepository) {
        CategoryEntity category = categoryRepository.findById(categoryId).get();

        return PostEntity.builder()
                .title(title)
                .content(content)
                .price(price)
                .category(category)
                .productState(Status.RESERVED)
                .imageUrl(imageUrl)
                .updatedAt(LocalDateTime.now())
                //끌올 설정
                .lastReposting(LocalDateTime.now())
                .build();
    }
}
