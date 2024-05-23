package com.c_comachi.inused.domain.keyword.dto.response;

import com.c_comachi.inused.domain.keyword.dto.KeywordInfo;
import com.c_comachi.inused.domain.keyword.entity.KeywordEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetKeyWordResponseDto extends ResponseDto {
    private List<KeywordInfo> keywordInfos;

    public GetKeyWordResponseDto(List<KeywordInfo> keywordInfos) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.keywordInfos = keywordInfos;
    }

    public static ResponseEntity<GetKeyWordResponseDto> success(List<KeywordInfo> keywordInfos){
        GetKeyWordResponseDto result = new GetKeyWordResponseDto(keywordInfos);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
