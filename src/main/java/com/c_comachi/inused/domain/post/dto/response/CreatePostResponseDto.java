package com.c_comachi.inused.domain.post.dto.response;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class CreatePostResponseDto extends ResponseDto {
    private String user;
    private String title;
    private String content;
    private Integer price;
    private Integer wishCount;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private CategoryEntity category;
    private Status productState;


    private CreatePostResponseDto(PostEntity post, UserEntity user){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.user = user.getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.wishCount = post.getWishCount();
        this.viewCount = post.getViewCount();
        this.createdAt = post.getCreatedAt();
        this.category = post.getCategory();
        this.productState = post.getProductState();
    }

    public static ResponseEntity<CreatePostResponseDto> success(PostEntity post,UserEntity user) {
        CreatePostResponseDto result = new CreatePostResponseDto(post,user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
