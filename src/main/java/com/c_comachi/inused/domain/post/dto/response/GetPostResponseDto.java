package com.c_comachi.inused.domain.post.dto.response;

import com.c_comachi.inused.domain.post.dto.MainPostInfo;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class GetPostResponseDto extends ResponseDto {
    private MainPostInfo post;

    private GetPostResponseDto(MainPostInfo post){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.post = post;
    }

    public static ResponseEntity<GetPostResponseDto> success(MainPostInfo post) {
        GetPostResponseDto result = new GetPostResponseDto(post);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
