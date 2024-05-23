package com.c_comachi.inused.domain.keyword.dto.response;

import com.c_comachi.inused.domain.keyword.dto.KeywordInfo;
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
    List<PostEntity> mainPostInfos;

    public GetKeywordPostResponseDto(List<PostEntity> mainPostInfos) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.mainPostInfos = mainPostInfos;
    }

    public static ResponseEntity<GetKeywordPostResponseDto> success(List<PostEntity> mainPostInfos){
        GetKeywordPostResponseDto result = new GetKeywordPostResponseDto(mainPostInfos);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
