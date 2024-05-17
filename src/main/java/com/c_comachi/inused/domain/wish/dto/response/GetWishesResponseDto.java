package com.c_comachi.inused.domain.wish.dto.response;

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
public class GetWishesResponseDto extends ResponseDto {
    private List<MainPostInfo> mainPostInfos;

    public GetWishesResponseDto(List<MainPostInfo> mainPostInfos) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.mainPostInfos = mainPostInfos;
    }

    public static ResponseEntity<GetWishesResponseDto> success(List<MainPostInfo> mainPostInfos) {
        GetWishesResponseDto result = new GetWishesResponseDto(mainPostInfos);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
