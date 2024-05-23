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
    private List<KeywordInfo> keywords;
    private Integer keywordNum;

    public GetKeyWordResponseDto(List<KeywordInfo> keywords) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.keywords = keywords;
        this.keywordNum = keywords.size();
    }

    public static ResponseEntity<GetKeyWordResponseDto> success(List<KeywordInfo> keywords){
        GetKeyWordResponseDto result = new GetKeyWordResponseDto(keywords);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
