package com.c_comachi.inused.domain.post.dto.response;

import com.c_comachi.inused.domain.post.dto.MainPostInfo;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
public class GetAllPostResponseDto extends ResponseDto {
    private List<MainPostInfo> posts;

    private GetAllPostResponseDto(List<MainPostInfo> posts){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.posts = posts;

    }

    public static ResponseEntity<? super GetAllPostResponseDto> success(List<MainPostInfo> posts){
        GetAllPostResponseDto result = new GetAllPostResponseDto(posts);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
