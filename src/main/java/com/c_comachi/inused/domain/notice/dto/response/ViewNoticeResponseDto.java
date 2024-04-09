package com.c_comachi.inused.domain.notice.dto.response;

import com.c_comachi.inused.domain.notice.entity.NoticeEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Getter
public class ViewNoticeResponseDto extends ResponseDto{

    private List<NoticeEntity> notices;


    private ViewNoticeResponseDto(List<NoticeEntity> notices){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.notices = notices;
    }

    public static ResponseEntity<? super ViewNoticeResponseDto> success(List<NoticeEntity> notices){
        ViewNoticeResponseDto result = new ViewNoticeResponseDto(notices);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
