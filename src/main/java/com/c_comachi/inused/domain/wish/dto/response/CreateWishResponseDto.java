package com.c_comachi.inused.domain.wish.dto.response;

import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreateWishResponseDto extends ResponseDto {
    private long userId;

    private long postId;

    private CreateWishResponseDto(UserEntity user,PostEntity post){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = user.getId();
        this.postId = post.getId();
    }

    public static ResponseEntity<CreateWishResponseDto> success(UserEntity user,PostEntity post) {
        CreateWishResponseDto result = new CreateWishResponseDto(user,post);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
