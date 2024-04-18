package com.c_comachi.inused.domain.post.dto.response;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdatePostResponseDto extends ResponseDto {
    private String title;
    private String content;
    private Integer price;
    private CategoryEntity category;
    private Status productState;

    private LocalDateTime updatedAt;
    private LocalDateTime lastReposting;

    private UpdatePostResponseDto(PostEntity post){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.category = post.getCategory();
        this.productState = post.getProductState();
        this.updatedAt = post.getUpdatedAt();
        this.lastReposting = post.getLastReposting();
    }

    public static ResponseEntity<UpdatePostResponseDto> success(PostEntity post) {
        UpdatePostResponseDto result = new UpdatePostResponseDto(post);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
