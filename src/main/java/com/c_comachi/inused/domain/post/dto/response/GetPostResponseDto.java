package com.c_comachi.inused.domain.post.dto.response;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.domain.users.dto.response.LoginResponseDto;
import com.c_comachi.inused.domain.users.dto.response.TokenDto;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetPostResponseDto extends ResponseDto {
    private String title;
    private String content;
    private Integer price;
    private CategoryEntity category;
    private Status productState;
    private Integer wishCount;
    private Integer viewCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastReposting;

    private GetPostResponseDto(PostEntity post){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.category = post.getCategory();
        this.productState = post.getProductState();
        this.updatedAt = post.getUpdatedAt();
        this.lastReposting = post.getLastReposting();
        this.wishCount = post.getWishCount();
        this.viewCount = post.getViewCount();
        this.createdAt = post.getCreatedAt();
    }

    public static ResponseEntity<GetPostResponseDto> success(PostEntity post) {
        GetPostResponseDto result = new GetPostResponseDto(post);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
