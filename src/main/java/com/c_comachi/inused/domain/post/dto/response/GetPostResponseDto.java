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
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetPostResponseDto extends ResponseDto {
    private PostEntity post;
    private boolean check_liked;

    private GetPostResponseDto(PostEntity post, boolean check_liked){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.post = post;
        this.check_liked = check_liked;
    }

    public static ResponseEntity<GetPostResponseDto> success(PostEntity post, boolean check_liked) {
        GetPostResponseDto result = new GetPostResponseDto(post, check_liked);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
