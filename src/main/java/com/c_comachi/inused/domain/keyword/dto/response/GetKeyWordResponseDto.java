package com.c_comachi.inused.domain.keyword.dto.response;

import com.c_comachi.inused.domain.keyword.entity.KeywordEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetKeyWordResponseDto extends ResponseDto {
    private List<KeywordEntity> keywordEntityList;

    public GetKeyWordResponseDto(List<KeywordEntity> keywordEntityList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.keywordEntityList = keywordEntityList;
    }

    public static ResponseEntity<GetKeyWordResponseDto> success(List<KeywordEntity> keywordEntityList){
        GetKeyWordResponseDto result = new GetKeyWordResponseDto(keywordEntityList);
        return ResponseEntity.ok(result);
    }
}
