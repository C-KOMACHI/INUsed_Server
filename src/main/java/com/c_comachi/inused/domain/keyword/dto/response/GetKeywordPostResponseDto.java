package com.c_comachi.inused.domain.keyword.dto.response;

import com.c_comachi.inused.domain.keyword.dto.KeywordInfo;
import com.c_comachi.inused.domain.keyword.dto.KeywordPost;
import com.c_comachi.inused.domain.post.dto.MainPostInfo;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetKeywordPostResponseDto extends ResponseDto {
    List<KeywordPost> posts;

    public GetKeywordPostResponseDto(List<KeywordPost> posts) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.posts = posts;
    }

    public static ResponseEntity<GetKeywordPostResponseDto> success(List<KeywordPost> posts){
        GetKeywordPostResponseDto result = new GetKeywordPostResponseDto(posts);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
